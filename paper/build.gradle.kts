plugins {
    id("java-library")
}

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/public/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    api(project(":corn-spigot"))

    compileOnlyApi("com.destroystokyo.paper:paper-api:1.16.4-R0.1-SNAPSHOT") {
        exclude(module = "guava")
        exclude(module = "gson")
        exclude(module = "snakeyaml")
        exclude(module = "commons-lang")
    }
}
