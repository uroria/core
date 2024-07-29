plugins {
    alias(libs.plugins.minotaur)
    alias(libs.plugins.hangarPublish)
}

dependencies {
    api(project(":core-paper"))
    api(project(":core-velocity"))
    api(project(":core-fabric"))
}

val changelogDescription = "See [Github](https://github.com/uroria/core) for release notes."
val archiveJar = tasks.shadowJar.flatMap { it.archiveFile }

modrinth {
    token = System.getenv("MODRINTH_TOKEN")
    projectId = "uroria-core"
    versionNumber = project.version.toString()
    versionType = "release"
    uploadFile.set(archiveJar)
    gameVersions.addAll("1.21")
    loaders.addAll("velocity", "paper")
    changelog.set(changelogDescription)
}

hangarPublish {
    publications.register("plugin") {
        apiKey.set(System.getenv("HANGAR_TOKEN"))
        version = project.version.toString()
        channel = "release"
        id = "uroria-core"
        changelog.set(changelogDescription)
        platforms {
            paper {
                jar.set(archiveJar)
                platformVersions.addAll("1.21")
            }
            velocity {
                jar.set(archiveJar)
                platformVersions.addAll("3.3")
            }
        }
    }
}