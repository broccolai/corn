plugins {
    id("ca.stellardrift.polyglot-version-catalogs") version "5.0.0"
}

rootProject.name = "corn-parent"

basicProjects("misc", "context", "properties")
minecraftProjects("spigot", "paper")

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
