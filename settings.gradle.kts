include("core", "spigot", "paper", "adventure", "context")

rootProject.name = "corn-parent"

findProject(":core")?.name = "corn-core"
findProject(":spigot")?.name = "corn-spigot"
findProject(":paper")?.name = "corn-paper"
findProject(":adventure")?.name = "corn-adventure"
findProject(":context")?.name = "corn-context"
