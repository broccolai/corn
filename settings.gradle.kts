include("core", "spigot")

rootProject.name = "corn-parent"

findProject(":core")?.name = "corn-core"
findProject(":spigot")?.name = "corn-spigot"