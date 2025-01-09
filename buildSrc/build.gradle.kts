plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation(libs.vineflower)
    implementation(libs.diffpath)
    implementation(libs.asm)
}
