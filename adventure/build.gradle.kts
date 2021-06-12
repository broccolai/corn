repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    api(project(":corn-core"))
    api(project(":corn-paper"))

    implementation("net.kyori:adventure-api:4.4.0")
    implementation("net.kyori:adventure-text-serializer-bungeecord:4.0.0-SNAPSHOT")
}
