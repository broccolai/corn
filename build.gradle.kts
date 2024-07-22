import com.adarshr.gradle.testlogger.TestLoggerPlugin
import com.adarshr.gradle.testlogger.theme.ThemeType
import net.kyori.indra.IndraCheckstylePlugin
import net.kyori.indra.IndraPlugin
import net.kyori.indra.IndraPublishingPlugin

plugins {
    alias(libs.plugins.indra.base)
    alias(libs.plugins.indra.publishing) apply false
    alias(libs.plugins.indra.checkstyle) apply false
    alias(libs.plugins.test.logger)
    alias(libs.plugins.versions)
}

group = "love.broccolai.corn"
version = "4.0.0-SNAPSHOT"
description = "extremely opinionated mostly personal java utilities"

subprojects {
    apply<IndraPlugin>()
    apply<IndraPublishingPlugin>()
    apply<IndraCheckstylePlugin>()
    apply<TestLoggerPlugin>()

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly(rootProject.libs.jspecify)

        testImplementation(rootProject.libs.truth)

        testImplementation(rootProject.libs.junit.api)
        testImplementation(rootProject.libs.junit.engine)
    }

    indra {
        mitLicense()

        checkstyle("9.0")

        javaVersions {
            target(21)
            testWith(21)
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
                    developer {
                        id.set("TehBrian")
                        email.set("tehbrian@proton.me")
                    }
                }
            }
        }
    }

    testlogger {
        theme = ThemeType.MOCHA_PARALLEL
        showPassed = true
    }
}

tasks.withType<Jar> {
    onlyIf { false }
}
