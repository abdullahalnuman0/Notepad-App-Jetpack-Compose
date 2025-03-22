package dev.abdullah.noteapp.feature_note.domin.use_case

import dev.abdullah.noteapp.feature_note.domin.model.InvalidNoteException
import dev.abdullah.noteapp.feature_note.domin.model.Note
import dev.abdullah.noteapp.feature_note.domin.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty")
        }
        if (note.title.isBlank())
            throw InvalidNoteException("The content of the note can't be empty")

        repository.insertNote(note)
    }
}