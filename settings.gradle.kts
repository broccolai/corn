rootProject.name = "corn-parent"

projects("misc", "context", "properties", "minecraft")

fun projects(vararg names: String) {
    include(*names)

    names.forEach {
        project(":$it").name = "corn-$it"
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
