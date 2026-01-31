package dev.abdullah.noteapp.feature_note.domin.util

import kotlinx.serialization.Serializable

interface NavRoutes

@Serializable
data object NavNoteView : NavRoutes

@Serializable
data class NavAddEditNote(val id: Int?) : NavRoutes