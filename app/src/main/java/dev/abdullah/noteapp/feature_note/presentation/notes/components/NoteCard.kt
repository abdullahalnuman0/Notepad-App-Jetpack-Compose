package dev.abdullah.noteapp.feature_note.presentation.notes.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.abdullah.noteapp.feature_note.domin.model.Note
import dev.abdullah.noteapp.feature_note.domin.util.formatTimeAgo
import dev.abdullah.noteapp.feature_note.domin.util.getCategoryBackgroundColor
import dev.abdullah.noteapp.feature_note.domin.util.getCategoryTextColor

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NoteCard(
    note: Note,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit,
    onEdit: () -> Unit,
    onShare: () -> Unit,
    onPinOrUnpin: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.05f),
                spotColor = Color.Black.copy(alpha = 0.05f)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = note.category!!.getCategoryBackgroundColor()
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Category and Menu
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                note.category.let { category ->
                    CategoryChip(
                        category = category,
                        color = category.getCategoryTextColor(),
                        id = note.id,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }

                // 📌 PIN ICON
                if (note.isPinned) {
                    Icon(
                        imageVector = Icons.Default.PushPin,
                        contentDescription = "Pinned",
                        modifier = Modifier
//                            .align(Alignment.TopEnd)
//                            .padding(8.dp)
                            .size(16.dp)
                            .background(
                                MaterialTheme.colorScheme.surface,
                                CircleShape
                            )
                            .padding(4.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Box {
                    IconButton(
                        onClick = { isMenuExpanded = true },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "More options",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    NoteMenu(
                        isPinned = note.isPinned,
                        expanded = isMenuExpanded,
                        onDismiss = { isMenuExpanded = false },
                        onEdit = onEdit,
                        onDelete = onDelete,
                        onShare = onShare,
                        onPinOrUnpin = onPinOrUnpin
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.sharedElement(
                    sharedContentState = rememberSharedContentState("title/${note.id}"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Content based on note type
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = FontFamily.Serif
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.sharedElement(
                    sharedContentState = rememberSharedContentState("content/${note.id}"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Timestamp
            Text(
                text = formatTimeAgo(note.lastUpdated),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        }
    }

}
