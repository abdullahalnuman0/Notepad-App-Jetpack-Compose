package dev.abdullah.noteapp.feature_note.domin.use_case

import dev.abdullah.noteapp.feature_note.domin.model.Note
import dev.abdullah.noteapp.feature_note.domin.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id:Int):Note?{
        return repository.getNoteById(id)
    }
}