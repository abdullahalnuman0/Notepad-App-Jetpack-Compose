package dev.abdullah.noteapp.feature_note.domin.use_case

data class NoteUseCases(
    val observeNotesUseCase: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNote: AddNoteUseCase,
    val getNote :GetNoteUseCase
)