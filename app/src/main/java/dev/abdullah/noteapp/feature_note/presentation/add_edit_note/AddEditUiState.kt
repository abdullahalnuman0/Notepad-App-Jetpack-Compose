package dev.abdullah.noteapp.feature_note.presentation.add_edit_note

import dev.abdullah.noteapp.feature_note.domin.model.Note
import java.util.Date

data class AddEditUiState(
    val oldNote: Note?=null,
    val category: String? = null,
    val showCategoryDialog: Boolean=false,
    val title: String = "",
    val content: String = "",
    val lastUpdateTime: Long = System.currentTimeMillis(),

    )
