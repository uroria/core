description = "Core Library"

dependencies {
    api(libs.fastutil)
    api(libs.gson)
    api(libs.slf4j)
    api(libs.sentry)
    api(libs.toml4j)
    api(libs.bundles.adventure)

    compileOnlyApi(libs.lombok)
    annotationProcessor(libs.lombok)

    compileOnlyApi(libs.jetbrainsAnnotations)
    annotationProcessor(libs.jetbrainsAnnotations)

    testImplementation(libs.bundles.junit)
}