import sh.miles.voidcr.task.ApplyPatchesTask
import sh.miles.voidcr.task.BuildPatchesTask
import sh.miles.voidcr.task.DecompileTask
import sh.miles.voidcr.task.FilterZipTask
import sh.miles.voidcr.task.SetupSourcesTask

plugins {
    java
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

val crVersion = "0.3.11"

group = rootProject.group
version = rootProject.name

repositories {
    mavenCentral()
    maven("https://jitpack.io") {
        content {
            includeGroup("com.github.Hangman")
        }
    }
}

val cosmicReach by configurations.creating {
    extendsFrom(configurations.implementation.get())
}

sourceSets {
    val cosmicReach by creating {
        java.srcDirs("src/cosmic-reach/java")
        resources.srcDirs("src/cosmic-reach/resources")

        compileClasspath += sourceSets["main"].compileClasspath
        runtimeClasspath += sourceSets["main"].runtimeClasspath
    }

    sourceSets["main"].compileClasspath += sourceSets["cosmicReach"].output
    sourceSets["main"].runtimeClasspath += sourceSets["cosmicReach"].output
}

dependencies {
    implementation(libs.libgdx.api)
    implementation(libs.libgdx.headless)
    implementation(libs.tuningfork)
    implementation(libs.netty.all)

    cosmicReach(sourceSets["main"].output)
}

tasks.compileJava {
    source(sourceSets["main"].java, sourceSets["cosmicReach"].java)
}

tasks.shadowJar {
    manifest {
        attributes(
            "Implementation-Title" to "VoidCR",
            "Implementation-Version" to rootProject.version,
            "Main-Class" to "sh.miles.voidcr.Main"
        )
    }

    from(project.layout.buildDirectory.file("generated/cosmic-reach-assets"))

    dependsOn(generateResources)
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.register("setup") {
    group = "voidcr-setup"
}

val filterJar by tasks.registering(FilterZipTask::class) {
    group = "voidcr-setup"
    val exclusions = listOf(
        "de/",
        "com/",
        "io/",
    )

    this.inputJar = file("decompile/Cosmic-Reach-Server-$crVersion.jar")
    this.outputJar = file("decompile/Cosmic-Reach-Server-$crVersion-filtered.jar")
    this.filterFunction.set { entry ->
        for (exclusion in exclusions) {
            if (entry.startsWith(exclusion)) {
                return@set false
            }
        }

        if (!entry.endsWith(".class")) {
            return@set false
        }

        return@set true
    }
}

val decompileJar by tasks.registering(DecompileTask::class) {
    group = "voidcr-setup"
    this.decompileTarget = file("decompile/Cosmic-Reach-Server-$crVersion-filtered.jar")
    this.decompilerOutput = file("decompile/Cosmic-Reach-Server-$crVersion-decompiled.jar")
    this.decompilerArguments = listOf(
        "-dcc=1",
        "-ega=1",
        "-log=WARN",
        "-pll=120",
        "--file",
    )
}

val setupSources by tasks.registering(SetupSourcesTask::class) {
    group = "voidcr-setup"

    this.patchedJar = file("decompile/Cosmic-Reach-Server-$crVersion-patched.jar")
    this.sourceDir = file("src/cosmic-reach/java")
}

val applyPatches by tasks.registering(ApplyPatchesTask::class) {
    group = "voidcr-patching"
    this.patchDir = file("patches")
    this.inputFile = file("decompile/Cosmic-Reach-Server-$crVersion-decompiled.jar")
    this.outputJar = file("decompile/Cosmic-Reach-Server-$crVersion-patched.jar")
    this.failedPatchesJar = file("decompile/Cosmic-Reach-Server-$crVersion-failed-patched.jar")
}

val buildPatches by tasks.registering(BuildPatchesTask::class) {
    group = "voidcr-patching"
    this.patchDir = file("patches")
    this.sourceDir = file("src/cosmic-reach/java")
    this.decompiledJar = file("decompile/Cosmic-Reach-Server-$crVersion-decompiled.jar")
}

val generateResources by tasks.registering(Copy::class) {
    from(zipTree("decompile/Cosmic-Reach-Server-$crVersion.jar"))
    include("base/**", "build_assets/**", "icons/**", "post_build/**", "assets.txt")
    into(project.layout.buildDirectory.file("generated/cosmic-reach-assets"))
}
