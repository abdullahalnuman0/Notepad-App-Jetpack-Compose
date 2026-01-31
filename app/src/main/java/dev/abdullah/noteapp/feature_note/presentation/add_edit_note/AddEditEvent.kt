package dev.abdullah.noteapp.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditEvent {

    data object ToggleCategoryDialog : AddEditEvent()
    data class EnteredCategory(val value: String) : AddEditEvent()
    data class EnteredTitle(val value: String) : AddEditEvent()
    data class EnteredContent(val value: String) : AddEditEvent()
//    data class ChangeColor(val color: Int) : AddEditEvent()
    data object SaveNote : AddEditEvent()

}
