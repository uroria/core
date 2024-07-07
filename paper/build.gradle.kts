import org.apache.tools.ant.filters.ReplaceTokens

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.paperApi)
    annotationProcessor(libs.paperApi)

    testImplementation(libs.bundles.junit)
}