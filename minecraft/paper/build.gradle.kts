repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    api(projects.cornMinecraftSpigot)

    compileOnlyApi(libs.paper.api) {
        exclude(module = "guava")
        exclude(module = "gson")
        exclude(module = "snakeyaml")
        exclude(module = "commons-lang")
    }
}
