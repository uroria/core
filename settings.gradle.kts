rootProject.name = "core"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("versions.toml"))
        }
    }
}

include("core", "universal")

sequenceOf(
    "paper",
    "velocity",
    "fabric"
).forEach {
    include("core-$it")
}