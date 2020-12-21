plugins {
    java
    `java-library`
    `maven-publish`
    checkstyle
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "checkstyle")

    group = "broccolai.corn"
    version = "1.0.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/public/")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }

    dependencies {
        checkstyle("ca.stellardrift:stylecheck:0.1")
        compileOnly("org.checkerframework:checker-qual:3.8.0")

        testImplementation("com.google.truth:truth:1.1")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.3")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.6.3")
    }

    tasks {
        test {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        checkstyle {
            val configRoot = File(rootProject.projectDir, ".checkstyle")
            toolVersion = "8.34"
            configDirectory.set(configRoot)
            configProperties["basedir"] = configRoot.absolutePath
        }
    }

    publishing {
        repositories {
            maven {
                name = "repo_broccolai"
                url = uri("https://repo.broccol.ai/releases")
                credentials {
                    username = System.getenv("REPO_USERNAME")
                    password = System.getenv("REPO_PASSWORD")
                }
            }
        }
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }
    }

    configure<JavaPluginConvention> {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}
