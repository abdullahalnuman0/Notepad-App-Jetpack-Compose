package dev.abdullah.noteapp.feature_note.presentation.notes

import dev.abdullah.noteapp.feature_note.domin.model.Note
import dev.abdullah.noteapp.feature_note.domin.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    data object RestoreNote:NotesEvent()
    data object ToggleOrderSection:NotesEvent()
}