import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin
import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.shadow)
    id("com.gradleup.nmcp") version("0.0.8")
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

nmcp {
    publishAllPublications {
        project(":")

        username = System.getenv("SONATYPE_USERNAME")
        password = System.getenv("SONATYPE_PASSWORD")
        publicationType = "AUTOMATIC"
    }
}


tasks {
    publishing.publications.create<MavenPublication>("maven") {
        groupId = "com.uroria"
        artifactId = "core"
        version = project.version.toString()

        from(project.components["java"])

        pom {
            name.set(this@create.artifactId)
            description.set(projectDescription)
            url.set("https://github.com/uroria/core")

            licenses {
                license {
                    name.set("Apache 2.0")
                    url.set("https://github.com/uroria/core/blob/main/LICENSE")
                }
            }

            developers {
                developer {
                    id.set("julian-siebert")
                    name.set("Julian Siebert")
                    organization.set("Uroria")
                    organizationUrl.set("https://github.com/uroria")
                    email.set("mail@julian-siebert.de")
                }
            }

            issueManagement {
                system.set("GitHub")
                url.set("https://github.com/uroria/core/issues")
            }

            scm {
                connection.set("scm:git:git://github.com/uroria/core.git")
                developerConnection.set("scm:git:git@github.com:uroria/uroria.git")
                url.set("https://github.com/uroria/core")
                tag.set("HEAD")
            }

            ciManagement {
                system.set("GitHub Actions")
                url.set("https://github.com/uroria/core/actions")
            }
        }
    }
}