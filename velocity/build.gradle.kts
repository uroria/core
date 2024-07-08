repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(rootProject)
    annotationProcessor(rootProject)

    compileOnly(libs.velocityApi)
    annotationProcessor(libs.velocityApi)

    testImplementation(libs.bundles.junit)
}