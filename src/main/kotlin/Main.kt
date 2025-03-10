import menu.ArchiveMenu
import orkestrator.ArchiveOrkestrator

fun main() {
    val archiveOrkestrator = ArchiveOrkestrator()
    val console = Console()
    ArchiveMenu(archiveOrkestrator, console).showMainMenu()
}