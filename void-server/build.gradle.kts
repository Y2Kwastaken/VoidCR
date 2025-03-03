import sh.miles.voidcr.task.*

plugins {
    java
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

val crVersion = "0.3.27"

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

sourceSets {
    main {
        java.srcDir(files("src/cosmic-reach/java"))
    }
}

dependencies {
    // Cosmic Reach
    implementation(libs.libgdx.api)
    implementation(libs.libgdx.headless)
    implementation(libs.tuningfork)
    implementation(libs.netty.all)
    implementation(libs.lz4)
    // Cosmic Reach

    implementation(libs.gson)
    implementation(libs.guava)
    implementation(platform(libs.log4j.bom))
    implementation(libs.log4j.api)
    runtimeOnly(libs.log4j.core)
    implementation(libs.jspecify)

    implementation(project(":void-api"))
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

val filterJar by tasks.registering(FilterAndTransformZipTask::class) {
    group = "voidcr-setup"
    val exclusions = listOf(
        "de/",
        "com/",
        "io/",
        "net/jpountz/"
    )

    this.inputJar = file("decompile/Cosmic-Reach-Server-$crVersion.jar")
    this.outputJar = file("decompile/Cosmic-Reach-Server-$crVersion-filtered.jar")
    this.atFile = file("data/voidcr.ajex")
    this.filterFunction.set { entry ->
        for (exclusion in exclusions) {
            if (entry.startsWith(exclusion)) {
                return@set false
            }
        }

        return@set entry.endsWith(".class")
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

    dependsOn(filterJar)
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

    dependsOn(decompileJar)
}

val applyPatchesFuzzy by tasks.registering(ApplyPatchesFuzzyTask::class) {
    group = "voidcr-patching"
    this.patchDir = file("patches")
    this.inputFile = file("decompile/Cosmic-Reach-Server-$crVersion-decompiled.jar")
    this.outputJar = file("decompile/Cosmic-Reach-Server-$crVersion-patched.jar")
    this.failedPatchesJar = file("decompile/Cosmic-Reach-Server-$crVersion-failed-patched.jar")

    dependsOn(decompileJar)
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

val downloadCosmicReachServer by tasks.registering(DownloadCosmicReachServer::class) {
    group = "voidcr-setup"

    this.archiveRepoUrl = "https://raw.githubusercontent.com/CRModders/CosmicArchive/main/versions/"
    this.phase = "pre-alpha"
    this.CRVersion = crVersion
    this.outputJar = file("decompile/Cosmic-Reach-Server-$crVersion.jar")
}

tasks.register("setup") {
    group = "voidcr-setup"

    filterJar.get().mustRunAfter(downloadCosmicReachServer)
    setupSources.get().mustRunAfter(applyPatches)
    dependsOn(downloadCosmicReachServer, filterJar, decompileJar, applyPatches, setupSources)
}

tasks.register("update") {
    group = "voidcr-setup"

    setupSources.get().mustRunAfter(applyPatchesFuzzy)
    dependsOn(filterJar, decompileJar, applyPatchesFuzzy, setupSources)
}
