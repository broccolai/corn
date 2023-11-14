rootProject.name = "corn-parent"

projects("trove", "context", "properties", "minecraft")

fun projects(vararg names: String) {
    include(*names)

    names.forEach {
        project(":$it").name = "corn-$it"
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
