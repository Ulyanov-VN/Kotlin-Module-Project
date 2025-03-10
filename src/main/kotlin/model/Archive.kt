package model

class Archive(
    val id: Int,
    val name: String,
    val notes: MutableList<Note> = mutableListOf()
)