package dev.abdullah.noteapp.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.abdullah.noteapp.feature_note.domin.model.Note
import dev.abdullah.noteapp.feature_note.domin.use_case.NoteUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddEditUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.EnteredCategory ->
                _uiState.update {
                    it.copy(
                        category = event.value,
                        showCategoryDialog = false,
                        lastUpdateDate = Date()
                    )
                }

            is AddEditEvent.EnteredContent ->
                _uiState.update {
                    it.copy(content = event.value, lastUpdateDate = Date())
                }

            is AddEditEvent.EnteredTitle ->
                _uiState.update {
                    it.copy(title = event.value, lastUpdateDate = Date())
                }

            AddEditEvent.SaveNote -> saveNote()

            AddEditEvent.ToggleCategoryDialog ->
                _uiState.update {
                    it.copy(showCategoryDialog = !it.showCategoryDialog)
                }
        }
    }


    private fun saveNote() {
        viewModelScope.launch {
            val s = _uiState.value
            val note = Note(
                title = s.title,
                content = s.content,
                category = s.category ?: "Other",
                lastUpdated = s.lastUpdateDate.time
            )
            val addNote = noteUseCases.addNote(note)
            println(addNote)
        }
    }


    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data object Back : UiEvent()
    }

}
