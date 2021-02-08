include("core", "spigot", "paper")

rootProject.name = "corn-parent"

findProject(":core")?.name = "corn-core"
findProject(":spigot")?.name = "corn-spigot"
findProject(":paper")?.name = "corn-paper"
