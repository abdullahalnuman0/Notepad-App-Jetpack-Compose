package dev.abdullah.noteapp.feature_note.presentation.add_edit_note

import java.util.Date

data class AddEditUiState(
    val id:Int?=null,
    val category: String? = null,
    val showCategoryDialog: Boolean=false,
    val title: String = "",
    val content: String = "",
    val lastUpdateDate: Date = Date()
)
