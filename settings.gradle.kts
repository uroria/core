rootProject.name = "core"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("versions.toml"))
        }
    }
}

sequenceOf(
    "paper",
    "velocity",
    "fabric",
    "universal"
).forEach {
    include("core-$it")
    project(":core-$it").projectDir = file(it)
}