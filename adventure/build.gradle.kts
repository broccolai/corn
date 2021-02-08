plugins {
    id("java-library")
}

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/public/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    api(project(":corn-paper"))
    implementation("net.kyori:adventure-api:4.4.0")
    implementation("net.kyori:adventure-text-serializer-bungeecord:4.0.0-SNAPSHOT")
}
