package dev.abdullah.noteapp.feature_note.presentation.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

class AppSnackbarVisuals(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    val type: Type
) : SnackbarVisuals {

    enum class Type {
        SUCCESS,
        ERROR,
        INFO
    }
}
