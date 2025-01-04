rootProject.name = "VoidCR"

gradle.rootProject {
    version = "1.0.0-SNAPSHOT"
    group = "sh.miles.voidcr"
}

include("void-server")
include("void-api")
include("void-test-plugin")
