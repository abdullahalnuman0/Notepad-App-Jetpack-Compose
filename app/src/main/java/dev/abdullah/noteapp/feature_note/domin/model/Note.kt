package dev.abdullah.noteapp.feature_note.domin.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date


@Entity
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val content: String,
    val category: String,

    val colorTag: Long = Color.White.value.toLong(),        // Room can handle Long
    val backgroundColor: Long = Color.White.value.toLong(), // Room can handle Long

    val lastUpdated: Long = Date().time, // Store timestamp as Long
    val isPinned: Boolean = false
)


class InvalidNoteException(message: String) : Exception(message)

/**
data class NoteNew(
    val id: Int,
    val title: String = "",
    val content: String = "",
    val category: String? = null,
    val colorTag: Color = Color(0xFF6B8E76),
    val backgroundColor: Color = Color.White,
    val lastUpdated: Date = Date(),
    val isPinned: Boolean = false,
)
* */