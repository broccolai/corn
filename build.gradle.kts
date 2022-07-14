import net.kyori.indra.IndraPlugin
import net.kyori.indra.IndraPublishingPlugin
import net.kyori.indra.IndraCheckstylePlugin
import net.kyori.indra.repository.sonatypeSnapshots

plugins {
    id("net.kyori.indra")
    id("net.kyori.indra.publishing") apply false
    id("net.kyori.indra.checkstyle") apply false
    id("com.adarshr.test-logger")
}

group = "broccolai.corn"
version = "3.1.0"
description = "extremely opinionated mostly personal java utilities"

subprojects {
    apply<IndraPlugin>()
    apply<IndraPublishingPlugin>()
    apply<IndraCheckstylePlugin>()
    apply<com.adarshr.gradle.testlogger.TestLoggerPlugin>()

    repositories {
        mavenCentral()
        sonatypeSnapshots()
    }

    dependencies {
        compileOnly(rootProject.libs.checker.qual)

        testImplementation(rootProject.libs.truth)

        testImplementation(rootProject.libs.junit.api)
        testImplementation(rootProject.libs.junit.engine)
    }

    indra {
        mitLicense()

        checkstyle("9.0")

        javaVersions {
            target(17)
            testWith(17)
        }

        github("broccolai", "corn") {
            ci(true)
            publishing(true)
        }

        publishReleasesTo("broccolai", "https://repo.broccol.ai/releases")
        publishSnapshotsTo("broccolai", "https://repo.broccol.ai/snapshots")

        configurePublications {
            pom {
                developers {
                    developer {
                        id.set("broccolai")
                        email.set("me@broccol.ai")
                    }
                }
            }
        }
    }

    testlogger {
        theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
        showPassed = true
    }
}

tasks.withType<Jar> {
    onlyIf { false }
}
