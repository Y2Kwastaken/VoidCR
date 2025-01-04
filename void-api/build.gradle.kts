plugins {
    `java-library`
}

group = rootProject.group
version = rootProject.name

repositories {
    mavenCentral()
}

dependencies {
    api(libs.jspecify)
    api(libs.gson)
    api(libs.guava)
    api(platform(libs.log4j.bom))
    api(libs.log4j.api)
}
