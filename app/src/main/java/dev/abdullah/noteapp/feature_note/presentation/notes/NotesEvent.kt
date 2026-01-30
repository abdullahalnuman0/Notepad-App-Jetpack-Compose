package dev.abdullah.noteapp.feature_note.presentation.notes

import dev.abdullah.noteapp.feature_note.domin.model.Note

sealed class NotesEvent {
    data class Search(val value: String) : NotesEvent()
    data class Sort(val option: SortOption) : NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    data object RestoreNote:NotesEvent()
}