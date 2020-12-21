plugins {
    id("java-library")
}

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/public/")
}

dependencies {
    api(project(":corn-core"))

    compileOnly("org.spigotmc:spigot-api:1.16.4-R0.1-SNAPSHOT") {
        exclude(module = "guava")
        exclude(module = "gson")
        exclude(module = "bungeecord-chat")
        exclude(module = "snakeyaml")
        exclude(module = "commons-lang")
    }
}
