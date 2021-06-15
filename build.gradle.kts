import net.kyori.indra.IndraPlugin
import net.kyori.indra.IndraPublishingPlugin
import net.kyori.indra.IndraCheckstylePlugin
import net.kyori.indra.repository.sonatypeSnapshots

plugins {
    id("net.kyori.indra")
    id("net.kyori.indra.publishing") apply false
    id("net.kyori.indra.checkstyle") apply false
}

group = "broccolai.corn"
version = "2.3.0-SNAPSHOT"
description = "extremely opinionated mostly personal java utilities"

subprojects {
    apply<IndraPlugin>()
    apply<IndraPublishingPlugin>()
    apply<IndraCheckstylePlugin>()

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

        javaVersions {
            target(16)
            testWith(16)
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
}

tasks.withType<Jar> {
    onlyIf { false }
}
