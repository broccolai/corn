repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    api(project(":corn-core"))

    compileOnly("org.spigotmc","spigot-api", Versions.SPIGOT_VERSION) {
        exclude(module = "guava")
        exclude(module = "gson")
        exclude(module = "bungeecord-chat")
        exclude(module = "snakeyaml")
        exclude(module = "commons-lang")
    }
}
