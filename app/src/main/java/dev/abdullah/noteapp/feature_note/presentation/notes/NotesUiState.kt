package dev.abdullah.noteapp.feature_note.presentation.notes

import dev.abdullah.noteapp.feature_note.domin.model.Note

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val selectedSort: SortOption = SortOption.RECENT,
    val searchQuery: String = ""
)
