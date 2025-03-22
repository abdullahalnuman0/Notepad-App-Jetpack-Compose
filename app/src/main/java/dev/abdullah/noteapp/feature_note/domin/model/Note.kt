package dev.abdullah.noteapp.feature_note.domin.model

import android.os.Message
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.abdullah.noteapp.ui.theme.BabyBlue
import dev.abdullah.noteapp.ui.theme.LightGreen
import dev.abdullah.noteapp.ui.theme.RedOrange
import dev.abdullah.noteapp.ui.theme.RedPink
import dev.abdullah.noteapp.ui.theme.Violet


@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {

    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }

}

class InvalidNoteException(message: String) : Exception(message)