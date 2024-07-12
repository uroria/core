import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin
import org.apache.tools.ant.filters.ReplaceTokens
import java.time.Duration

plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.shadow)
    signing
    alias(libs.plugins.nexusPublish)
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

tasks {
    nexusPublishing {
        useStaging.set(true)
        this.packageGroup.set("com.uroria")

        transitionCheckOptions {
            maxRetries.set(360) // 1h
            delayBetween.set(Duration.ofSeconds(10))
        }

        repositories.sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

            val sonatypeUsername = System.getenv("SONATYPE_USERNAME")
            val sonatypePassword = System.getenv("SONATYPE_PASSWORD")

            if (sonatypeUsername != null) {
                username.set(sonatypeUsername)
                password.set(sonatypePassword)
            }
        }
    }

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

    signing {
        isRequired = System.getenv("CI") != null

        val privateKey = System.getenv("GPG_PRIVATE_KEY")
        val keyPassphrase = System.getenv("GPG_PASSPHRASE")
        useInMemoryPgpKeys(privateKey, keyPassphrase)

        sign(publishing.publications)
    }
}