repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly(libs.spigot.api) {
        exclude(module = "gson")
        exclude(module = "bungeecord-chat")
        exclude(module = "snakeyaml")
        exclude(module = "commons-lang")
    }
}
