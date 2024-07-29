description = "Core Libraries Velocity plugin"

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api(project(":core"))
    annotationProcessor(project(":core"))

    compileOnly(libs.velocityApi)
    annotationProcessor(libs.velocityApi)

    testImplementation(libs.bundles.junit)
}