package dev.abdullah.noteapp.feature_note.data.data_source


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.abdullah.noteapp.feature_note.domin.model.Note
import dev.abdullah.noteapp.feature_note.domin.model.NoteTypeConverters


@Database(
    entities = [Note::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(NoteTypeConverters::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }

}