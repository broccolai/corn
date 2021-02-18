repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    api(project(":corn-spigot"))

    compileOnlyApi("com.destroystokyo.paper", "paper-api", Versions.PAPER_VERSION) {
        exclude(module = "guava")
        exclude(module = "gson")
        exclude(module = "snakeyaml")
        exclude(module = "commons-lang")
    }
}
