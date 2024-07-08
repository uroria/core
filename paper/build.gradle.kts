repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(rootProject)
    annotationProcessor(rootProject)

    compileOnly(libs.paperApi)
    annotationProcessor(libs.paperApi)

    testImplementation(libs.bundles.junit)
}