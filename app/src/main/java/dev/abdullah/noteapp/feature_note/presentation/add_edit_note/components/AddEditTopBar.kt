package dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTopBar(
    onBackClick: () -> Unit,
    onShare: () -> Unit,
    onSave: () -> Unit
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(
                onClick = onShare
            ) {
                Icon(Icons.Default.Share, contentDescription = "Share")
            }
            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = onSave
//                    {
//                    noteState = noteState.copy(
//                        wordCount = wordCount,
//                        lastEdited = Date()
//                    )
//                    onSaveNote(noteState)
//                }
                ,
                modifier = Modifier.shadow(
                    elevation = 4.dp,
                    shape = CircleShape,
                    ambientColor = Color(0xFF6B8E76).copy(alpha = 0.3f),
                    spotColor = Color(0xFF6B8E76).copy(alpha = 0.3f)
                )
            ) { Text("Done") }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.95f)
        )
    )
}