package menu

import UserInterface
import model.Archive
import orkestrator.ArchiveOrkestrator

class ArchiveMenu(
    private val archiveOrkestrator: ArchiveOrkestrator,
    private val ui: UserInterface
) {
    fun showMainMenu() {
        while (true) {
            ui.showMessage("\nГлавное меню:")
            ui.showMessage("1. Создать архив")
            ui.showMessage("2. Просмотреть архивы")
            ui.showMessage("0. Выход")

            when (ui.promptInt("Выберите действие:")) {
                1 -> createArchiveFlow()
                2 -> showArchivesFlow()
                0 -> return
                else -> ui.showMessage("Некорректный ввод")
            }
        }
    }

    private fun createArchiveFlow() {
        val name = ui.promptString("Введите название архива:")
        if (name.isEmpty()) {
            ui.showMessage("Ошибка: название не может быть пустым")
            return
        }

        try {
            val archive = archiveOrkestrator.createArchive(name)
            ui.showMessage("Архив '${archive.name}' создан!")
        } catch (e: IllegalArgumentException) {
            ui.showMessage("Ошибка: ${e.message}")
        }
    }

    private fun showArchivesFlow() {
        val archives = archiveOrkestrator.getAllArchives()
        if (archives.isEmpty()) {
            ui.showMessage("Нет доступных архивов")
            return
        }

        ui.showMessage("\nСписок архивов:")
        archives.forEachIndexed { index, archive ->
            ui.showMessage("${index + 1}. ${archive.name}")
        }

        handleArchiveSelection(archives)
    }

    private fun handleArchiveSelection(archives: List<Archive>) {
        val choice = ui.promptInt("Введите номер архива или 0 для возврата:") ?: return

        when {
            choice == 0 -> return
            choice in 1..archives.size -> {
                val archive = archives[choice - 1]
                NoteMenu(archive, ui).showNotesMenu()
            }
            else -> ui.showMessage("Некорректный выбор")
        }
    }
}