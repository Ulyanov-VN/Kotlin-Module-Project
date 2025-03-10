package orkestrator

import model.Archive

class ArchiveOrkestrator {
    private val archives = mutableListOf<Archive>()
    private var archiveCounter = 0

    fun createArchive(name: String): Archive {
        require(name.isNotBlank()) { "Имя архива не может быть пустым" }
        val archive = Archive(++archiveCounter, name.trim())
        archives.add(archive)
        return archive
    }

    fun getAllArchives() = archives.toList()
    fun getArchive(index: Int) = archives.getOrNull(index)
}