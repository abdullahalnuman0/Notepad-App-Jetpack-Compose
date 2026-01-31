package dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LinedNoteInput(
    modifier: Modifier = Modifier,
    noteContent: String,
    onValueChange: (String) -> Unit
) {
    // 1. Define shared measurements to ensure text and lines align
    val lineSpacingSp = 30.sp
    val lineSpacingDp = 30.dp
    val verticalPadding = 8.dp

    val lineColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f)
    val marginLineColor = MaterialTheme.colorScheme.error.copy(alpha = 0.3f) // Reddish margin

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        BasicTextField(
            value = noteContent,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontSize = 20.sp,
                lineHeight = lineSpacingSp, // Matches the drawing height
                fontFamily = FontFamily.Serif,
                color = MaterialTheme.colorScheme.onBackground
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val spacingPx = lineSpacingDp.toPx()
                    val verticalPaddingPx = verticalPadding.toPx()

                    // --- Draw Horizontal Lines ---
                    // We start drawing from the first baseline
                    var y = spacingPx + verticalPaddingPx
                    while (y < size.height) {
                        drawLine(
                            color = lineColor,
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                        y += spacingPx
                    }

                    // --- Draw Vertical Margin Line ---
                    val marginX = 4.dp.toPx() // Slight offset from left
                    drawLine(
                        color = marginLineColor,
                        start = Offset(marginX, 0f),
                        end = Offset(marginX, size.height),
                        strokeWidth = 2.dp.toPx()
                    )
                }
                .padding(
                    top = verticalPadding,
                    start = 12.dp
                ), // Start padding pushes text past margin line
            decorationBox = { innerTextField ->
                if (noteContent.isEmpty()) {
                    Text(
//                        text = "Begin writing here...",
                        text = "Begin writing your thoughts...",
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = lineSpacingSp,
                            fontFamily = FontFamily.Serif,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        )
                    )
                }
                innerTextField()
            }
        )
    }
}
