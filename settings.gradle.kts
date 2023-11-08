rootProject.name = "corn-parent"

basicProjects("misc", "context", "properties")
minecraftProjects("paper")

fun basicProjects(vararg names: String) {
    include(*names)

    names.forEach {
        project(":$it").name = "corn-$it"
    }
}

fun minecraftProjects(vararg names: String) {
    include(*names)

    names.forEach {
        project(":$it").apply {
            projectDir = file("minecraft/$it")
            name = "corn-minecraft-$it"
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
