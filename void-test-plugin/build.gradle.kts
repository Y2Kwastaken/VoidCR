plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jspecify)

    compileOnly(project(":void-api"))
    compileOnly(project(":void-server"))
}

val copyToTestServer by tasks.registering(Copy::class) {
    from(tasks.jar)
    into("../void-server/build/libs/plugins/")
}

tasks.build {
    dependsOn(copyToTestServer)
}
