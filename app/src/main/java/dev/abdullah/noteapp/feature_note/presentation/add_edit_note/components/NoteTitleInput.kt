package dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteTitleInput(
    modifier: Modifier = Modifier,
    title: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        value = title,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        decorationBox = { innerTextField ->
            if (title.isEmpty()) {
                Text(
                    text = "A title for your thoughts...",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )
            }
            innerTextField()
        },
        singleLine = true,
        maxLines = 1
    )
}