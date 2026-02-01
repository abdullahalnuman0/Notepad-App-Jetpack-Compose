package dev.abdullah.noteapp.feature_note.presentation.add_edit_note

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
            is AddEditEvent.EnteredCategory -> updateState {
                copy(
                    category = event.value,
                    showCategoryDialog = false,
                    lastUpdateTime = touchNote()
                )
            }

            is AddEditEvent.EnteredContent -> updateState {
                copy(content = event.value, lastUpdateTime = touchNote())
            }

            is AddEditEvent.EnteredTitle -> updateState {
                copy(title = event.value, lastUpdateTime = touchNote())
            }

            AddEditEvent.SaveNote -> saveNote()

            AddEditEvent.ToggleCategoryDialog -> updateState {
                copy(showCategoryDialog = !this.showCategoryDialog)
            }

            is AddEditEvent.GetOldNote -> getOldNote(event.id)
        }
    }

    private fun getOldNote(id: Int) {
        viewModelScope.launch {

            noteUseCases.getNote(id)?.let { note ->

                updateState {
                    copy(
                        oldNote = note,
                        category = note.category,
                        title = note.title,
                        content = note.content,
                        lastUpdateTime = note.lastUpdated
                    )
                }
            }
        }
    }


    private fun saveNote() {

        viewModelScope.launch {

            val s = _uiState.value
            if ((s.title.isEmpty() && s.content.isEmpty() && s.category == null) || (s.oldNote!=null && s.lastUpdateTime == s.oldNote.lastUpdated)) {
                _uiEvent.emit(UiEvent.Back)
                return@launch
            }

            val note = s.oldNote?.copy(
                title = s.title,
                content = s.content,
                category = s.category ?: "Other",
                lastUpdated = s.lastUpdateTime
            ) ?: Note(
                title = s.title,
                content = s.content,
                category = s.category ?: "Other",
                lastUpdated = s.lastUpdateTime
            )

            _uiEvent.emit(UiEvent.Back)
            noteUseCases.addNote(note)
        }
    }

    private inline fun updateState(
        crossinline reducer: AddEditUiState.() -> AddEditUiState
    ) {
        _uiState.update { current ->
            current.reducer()
        }
    }

    private fun touchNote() = System.currentTimeMillis()


    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data object Back : UiEvent()
    }

}
