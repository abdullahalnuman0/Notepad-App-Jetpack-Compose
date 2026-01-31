package dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.Note
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.noteColors

@Composable
fun BottomToolbar(
    noteState: Note,
    onFormatBold: () -> Unit,
    onFormatItalic: () -> Unit,
    onFormatBulleted: () -> Unit,
    onColorSelect: (Color) -> Unit,
    onAddImage: () -> Unit,
    onAddLink: () -> Unit,
    showColorPicker: Boolean,
    onShowColorPicker: (Boolean) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color.Black.copy(alpha = 0.1f),
                spotColor = Color.Black.copy(alpha = 0.1f)
            ),
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Formatting buttons
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                modifier = Modifier.padding(4.dp)
            ) {
                Row {
                    FormatButton(
                        icon = Icons.Default.FormatBold,
                        isActive = noteState.isBold,
                        onClick = onFormatBold
                    )
                    FormatButton(
                        icon = Icons.Default.FormatItalic,
                        isActive = noteState.isItalic,
                        onClick = onFormatItalic
                    )
                    FormatButton(
                        icon = Icons.Default.FormatListBulleted,
                        isActive = noteState.isBulleted,
                        onClick = onFormatBulleted
                    )
                }
            }

            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .height(24.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
            )

            // Color picker
            Row(
                modifier = Modifier.padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                noteColors.forEach { color ->
                    ColorCircle(
                        color = color,
                        isSelected = noteState.colorTag == color,
                        onClick = { onColorSelect(color) }
                    )
                }
            }

            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .height(24.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
            )

            // Media buttons
            Row {
                IconButton(
                    onClick = onAddImage,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Add Image")
                }
                IconButton(
                    onClick = onAddLink,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(Icons.Default.Link, contentDescription = "Add Link")
                }
            }
        }
    }

    // Color picker expanded view
    if (showColorPicker) {
        ColorPickerDialog(
            colors = noteColors,
            selectedColor = noteState.colorTag,
            onColorSelected = onColorSelect,
            onDismiss = { onShowColorPicker(false) }
        )
    }
}


@Composable
fun FormatButton(
    icon: ImageVector,
    isActive: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(40.dp)
            .shadow(
                elevation = if (isActive) 2.dp else 0.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = if (isActive) MaterialTheme.colorScheme.background
                else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isActive) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ColorCircle(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .background(color)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = MaterialTheme.colorScheme.background,
                shape = CircleShape
            )
            .padding(if (isSelected) 2.dp else 0.dp)
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 2.dp,
                        color = color,
                        shape = CircleShape
                    )
            )
        }
    }
}





@Composable
fun ColorPickerDialog(
    colors: List<Color>,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Choose Color",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    colors.forEach { color ->
                        ColorCircle(
                            color = color,
                            isSelected = color == selectedColor,
                            onClick = {
                                onColorSelected(color)
                                onDismiss()
                            }
                        )
                    }
                }
            }
        }
    }
}