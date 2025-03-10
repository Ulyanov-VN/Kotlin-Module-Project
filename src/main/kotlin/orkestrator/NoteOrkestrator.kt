package orkestrator

import model.Archive
import model.Note

class NoteOrkestrator {
    private var noteCounter = 0

    fun createNote(archive: Archive, title: String, content: String): Note {
        require(title.isNotBlank()) { "Название заметки не может быть пустым" }
        require(content.isNotBlank()) { "Содержимое заметки не может быть пустым" }
        val note = Note(++noteCounter, title.trim(), content.trim())
        archive.notes.add(note)
        return note
    }

    fun getNotes(archive: Archive) = archive.notes.toList()
    fun getNote(archive: Archive, index: Int) = archive.notes.getOrNull(index)
}