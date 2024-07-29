description = "Core Libraries Paper plugin"

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api(project(":core"))
    annotationProcessor(project(":core"))

    compileOnly(libs.paperApi)
    annotationProcessor(libs.paperApi)

    testImplementation(libs.bundles.junit)
}