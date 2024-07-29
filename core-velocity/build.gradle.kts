description = "Core Libraries Velocity plugin"

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(project(":core"))
    annotationProcessor(project(":core"))

    compileOnly(libs.velocityApi)
    annotationProcessor(libs.velocityApi)

    testImplementation(libs.bundles.junit)
}