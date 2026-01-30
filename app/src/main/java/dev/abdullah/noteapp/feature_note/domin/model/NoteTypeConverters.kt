package dev.abdullah.noteapp.feature_note.domin.model

import androidx.room.TypeConverter
import androidx.compose.ui.graphics.Color
import java.util.Date

class NoteTypeConverters {

    // Convert Long to Date
    @TypeConverter
    fun toDate(timestamp: Long): Date = Date(timestamp)

    // Convert Date to Long
    @TypeConverter
    fun fromDate(date: Date): Long = date.time
}

