repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.velocityApi)
    annotationProcessor(libs.velocityApi)

    testImplementation(libs.bundles.junit)
}