plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    maven("https://maven.miles.sh/snapshots")
}

dependencies {
    implementation(libs.vineflower)
    implementation(libs.diffpath)
    implementation(libs.asm)
    implementation("sh.miles.artisan:artisan-extensions:1.0.0-SNAPSHOT") {
        isChanging = true
    }
}
