repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly(libs.spigot.api)
    compileOnly("com.google.guava", "guava", "30.1.1-jre")
}
