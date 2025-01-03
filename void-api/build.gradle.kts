plugins {
    `java-library`
}

group = rootProject.group
version = rootProject.name

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.0") // for now switch to jspecify later

    api(libs.gson)
    api(libs.guava)
}
