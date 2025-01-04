plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jspecify)

    compileOnly(project(":void-api"))
}

val copyToTestServer by tasks.registering(Copy::class) {
    from(tasks.jar)
    into("../void-server/build/libs/plugins/")
}

tasks.build {
    dependsOn(copyToTestServer)
}
