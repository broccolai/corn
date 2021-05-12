import net.kyori.indra.IndraPlugin
import net.kyori.indra.IndraPublishingPlugin
import net.kyori.indra.IndraCheckstylePlugin
import net.kyori.indra.repository.sonatypeSnapshots

plugins {
    val indra = "2.0.3"
    id("net.kyori.indra") version indra
    id("net.kyori.indra.publishing") version indra apply false
    id("net.kyori.indra.checkstyle") version indra apply false
}

group = "broccolai.corn"
version = "2.1.0"
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
        compileOnly("org.checkerframework", "checker-qual", Versions.CHECKER_QUAL)

        testImplementation("com.google.truth", "truth", Versions.GOOGLE_TRUTH)

        testImplementation("org.junit.jupiter", "junit-jupiter-api", Versions.JUNIT)
        testImplementation("org.junit.jupiter", "junit-jupiter-engine", Versions.JUNIT)
    }

    indra {
        mitLicense()

        javaVersions {
            target(8)
            testWith(8, 11, 15)
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
