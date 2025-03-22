package dev.abdullah.noteapp.feature_note.domin.util

sealed class Screen (val route:String){
    data object NoteScreen :Screen("note_screen")
    data object AddEditScreen :Screen("add_edit_note_screen")
}
