package menu

import UserInterface
import model.Archive
import model.Note
import orkestrator.NoteOrkestrator

class NoteMenu(
    private val archive: Archive,
    private val ui: UserInterface
) {
    private val noteOrkestrator = NoteOrkestrator()

    fun showNotesMenu() {
        while (true) {
            ui.showMessage("\nАрхив: ${archive.name}")
            ui.showMessage("1. Создать заметку")
            ui.showMessage("2. Просмотреть заметки")
            ui.showMessage("0. Назад")

            when (ui.promptInt("Выберите действие:")) {
                1 -> createNoteFlow()
                2 -> showNotesFlow()
                0 -> return
                else -> ui.showMessage("Некорректный ввод")
            }
        }
    }

    private fun createNoteFlow() {
        val title = ui.promptString("Введите название заметки:")
        val content = ui.promptString("Введите содержимое заметки:")

        try {
            val note = noteOrkestrator.createNote(archive, title, content)
            ui.showMessage("Заметка '${note.title}' создана!")
        } catch (e: IllegalArgumentException) {
            ui.showMessage("Ошибка: ${e.message}")
        }
    }

    private fun showNotesFlow() {
        val notes = noteOrkestrator.getNotes(archive)
        if (notes.isEmpty()) {
            ui.showMessage("Нет заметок в этом архиве")
            return
        }

        ui.showMessage("\nСписок заметок:")
        notes.forEachIndexed { index, note ->
            ui.showMessage("${index + 1}. ${note.title}")
        }

        handleNoteSelection(notes)
    }

    private fun handleNoteSelection(notes: List<Note>) {
        val choice = ui.promptInt("Введите номер заметки или 0 для возврата:") ?: return

        when {
            choice == 0 -> return
            choice in 1..notes.size -> showNoteDetails(notes[choice - 1])
            else -> ui.showMessage("Некорректный выбор")
        }
    }

    private fun showNoteDetails(note: Note) {
        ui.showMessage("\nЗаметка: ${note.title}")
        ui.showMessage("Содержимое: ${note.text}")
        ui.promptString("Нажмите Enter для возврата...")
    }
}