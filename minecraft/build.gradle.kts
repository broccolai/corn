repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnlyApi(libs.paper.api)
    compileOnly("com.google.guava", "guava", "30.1.1-jre")
}
