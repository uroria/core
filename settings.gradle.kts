rootProject.name = "core"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("versions.toml"))
        }
    }
}

include("paper")
include("velocity")
include("universal")
