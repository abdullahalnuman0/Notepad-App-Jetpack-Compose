package dev.abdullah.noteapp.feature_note.presentation.notes


import androidx.compose.material3.SnackbarVisuals
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.abdullah.noteapp.feature_note.domin.model.Note
import dev.abdullah.noteapp.feature_note.domin.use_case.NoteUseCases
import dev.abdullah.noteapp.feature_note.presentation.components.AppSnackbarVisuals
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")
    private val selectedSort = MutableStateFlow(SortOption.RECENT)

    private val allNotes = noteUseCases.observeNotesUseCase()

    val uiState: StateFlow<NotesUiState> = combine(
        allNotes,
        searchQuery,
        selectedSort
    ) { notes, query, sort ->

        val filteredNotes = if (query.isBlank()) {
            notes
        } else {
            notes.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.content.contains(query, ignoreCase = true)
            }
        }

        /*val sortedNotes = when (sort) {
            SortOption.RECENT ->
                filteredNotes.sortedByDescending { it.lastUpdated }

            SortOption.UPDATED ->
                filteredNotes.sortedByDescending { it.lastUpdated }

            SortOption.ALPHABETICAL ->
                filteredNotes.sortedBy { it.title.lowercase() }

            SortOption.CATEGORY ->
                filteredNotes.sortedBy { it.category }
        }*/
        val sortedNotes = when (sort) {

            SortOption.RECENT,
            SortOption.UPDATED -> {
                filteredNotes
                    .sortedWith(
                        compareByDescending<Note> { it.isPinned } // pinned first
                            .thenByDescending { it.lastUpdated }   // then recent
                    )
            }

            SortOption.ALPHABETICAL -> {
                filteredNotes
                    .sortedWith(
                        compareByDescending<Note> { it.isPinned }
                            .thenBy { it.title.lowercase() }
                    )
            }

            SortOption.CATEGORY -> {
                filteredNotes
                    .sortedWith(
                        compareByDescending<Note> { it.isPinned }
                            .thenBy { it.category }
                    )
            }
        }


        NotesUiState(
            notes = sortedNotes,
            searchQuery = query,
            selectedSort = sort
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = NotesUiState()
    )

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var lastDeleteNote: Note? = null
    private var lastPinOrUnpinNote: Note? = null


    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Search -> searchQuery.update { event.value }

            is NotesEvent.Sort -> selectedSort.update { event.option }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    lastDeleteNote = event.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(lastDeleteNote ?: return@launch)
                    lastDeleteNote = null
                }
            }

            is NotesEvent.PinOrUnpin -> {
                viewModelScope.launch {

                    val note = (event.note ?: lastDeleteNote) ?: return@launch

                    val isPinned = note.isPinned
                    lastPinOrUnpinNote = note.copy(isPinned = !isPinned)

                    val visuals = try {

                        noteUseCases.addNote(lastPinOrUnpinNote ?: return@launch)
                        AppSnackbarVisuals(
                            message = if (isPinned) "Unpinned" else "Pinned",
                            actionLabel = if (isPinned) "Pin" else "Unpin",
                            type = AppSnackbarVisuals.Type.SUCCESS
                        )

                    } catch (_: Exception) {

                        AppSnackbarVisuals(
                            message = "Something want wrong!",
                            withDismissAction = true,
                            type = AppSnackbarVisuals.Type.ERROR
                        )

                    }
                    _uiEvent.emit(UiEvent.ShowSnackBar(visuals))
                }
            }
        }

    }


    sealed class UiEvent {
        data class ShowSnackBar(val visuals: SnackbarVisuals) : UiEvent()
    }
}
