import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin
import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.shadow)
}

version = System.getenv("CORE_VERSION") ?: "dev"
val channel = System.getenv("CORE_CHANNEL") ?: "local";

val projectDescription = "Uroria Core Java Libraries"

dependencies {
    api(libs.fastutil)
    api(libs.gson)
    api(libs.slf4j)
    api(libs.sentry)
    api(libs.toml4j)

    compileOnlyApi(libs.lombok)
    annotationProcessor(libs.lombok)

    compileOnlyApi(libs.jetbrainsAnnotations)
    annotationProcessor(libs.jetbrainsAnnotations)

    testImplementation(libs.bundles.junit)
}

java {
    withSourcesJar()
    withJavadocJar()
}

allprojects {
    apply<JavaLibraryPlugin>()
    apply<MavenPublishPlugin>()

    group = "com.uroria"
    version = rootProject.version
    description = projectDescription

    repositories {
        mavenCentral()
    }

    java.toolchain.languageVersion = JavaLanguageVersion.of(21)

    tasks {
        withType<Test> {
            useJUnitPlatform()

            minHeapSize = "256m"
            maxHeapSize = "512m"
        }

        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }

        withType<Javadoc> {
            (options as? StandardJavadocDocletOptions)?.apply {
                encoding = "UTF-8"

                addBooleanOption("html5", true)
                addStringOption("-release", "21")
                addStringOption("Xdoclint:-missing", "-quiet")
            }
        }

        withType<Jar> {
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }

        processResources {
            from(sourceSets.main.get().resources.srcDirs()) {
                filter<ReplaceTokens>("tokens" to mapOf("version" to project.version.toString()))
                filter<ReplaceTokens>("tokens" to mapOf("description" to projectDescription))

                duplicatesStrategy = DuplicatesStrategy.INCLUDE
            }
        }
    }
}

subprojects {
    apply<ShadowPlugin>()
}

sourceSets {
    main {
        java.srcDir(file("src/main/java"))
    }
}