package dev.abdullah.noteapp.feature_note.domin.use_case

import dev.abdullah.noteapp.feature_note.domin.model.InvalidNoteException
import dev.abdullah.noteapp.feature_note.domin.model.Note
import dev.abdullah.noteapp.feature_note.domin.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.insertNote(note)
    }
}