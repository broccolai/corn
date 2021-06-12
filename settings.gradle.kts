plugins {
    id("ca.stellardrift.polyglot-version-catalogs") version "5.0.0"
}

rootProject.name = "corn-parent"

cornProjects("core", "spigot", "paper", "adventure", "context")

fun cornProjects(vararg names: String) {
    include(*names)

    names.forEach {
        project(":$it").name = "corn-$it"
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
