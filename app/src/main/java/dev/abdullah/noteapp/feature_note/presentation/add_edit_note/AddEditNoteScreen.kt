package dev.abdullah.noteapp.feature_note.presentation.add_edit_note

// AddNoteScreen.kt
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.AddEditTopBar
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.BottomToolbar
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.CategorySelectionDialog
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.CategorySelector
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.FooterStats
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Data classes for state management
data class Note(
    val id: Int = 0,
    var title: String = "",
    var content: String = "",
    var category: String? = null,
    var colorTag: Color = Color(0xFF6B8E76),
    var lastEdited: Date = Date(),
    var wordCount: Int = 0,
    var isBold: Boolean = false,
    var isItalic: Boolean = false,
    var isBulleted: Boolean = false
)

// Color options for the note
val noteColors = listOf(
    Color(0xFF6B8E76),    // chart-1
    Color(0xFFD4B483),    // chart-2
    Color(0xFFC8A2C8),    // chart-3
    Color(0xFF8FBAC2),    // chart-4
    Color(0xFFD06A5F)     // chart-5
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
   navController: NavController
) {
    var noteState by remember { mutableStateOf(Note()) }
    var showCategoryDialog by remember { mutableStateOf(false) }
    var showColorPicker by remember { mutableStateOf(false) }

    // Calculate word count whenever content changes
    val wordCount = remember(noteState.content) {
        noteState.content.trim().split("\\s+".toRegex()).count { it.isNotEmpty() }
    }

    // Update last edited time
    val lastEditedFormatted = remember(noteState.content) {
        val sdf = SimpleDateFormat("MMM dd, yyyy · HH:mm", Locale.getDefault())
        sdf.format(Date())
    }

    Scaffold(
        topBar = {
            AddEditTopBar(
                onBackClick = { navController.popBackStack() },
                onShare = { },
                onSave = { }
            )
        },
        bottomBar = {
            BottomToolbar(
                noteState = noteState,
                onFormatBold = {
                    noteState = noteState.copy(isBold = !noteState.isBold)
                },
                onFormatItalic = {
                    noteState = noteState.copy(isItalic = !noteState.isItalic)
                },
                onFormatBulleted = {
                    noteState = noteState.copy(isBulleted = !noteState.isBulleted)
                },
                onColorSelect = { color ->
                    noteState = noteState.copy(colorTag = color)
                },
                onAddImage = {
                    // Implement camera/image picker functionality
                },
                onAddLink = {
                    // Implement link insertion functionality
                },
                showColorPicker = showColorPicker,
                onShowColorPicker = { showColorPicker = it }
            )
        },
        modifier = Modifier.navigationBarsPadding()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Background pattern
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(radius = 0.5.dp, edgeTreatment = BlurredEdgeTreatment.Rectangle)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.background,
                                MaterialTheme.colorScheme.background.copy(alpha = 0.7f)
                            ),
                            radius = 500f
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Category Selector
                CategorySelector(
                    category = noteState.category,
                    onClick = { showCategoryDialog = true }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Title Input
                BasicTextField(
                    value = noteState.title,
                    onValueChange = {
                        noteState = noteState.copy(title = it)
                    },
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
                        if (noteState.title.isEmpty()) {
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

                Spacer(modifier = Modifier.height(24.dp))

                // Content Input with lined paper effect
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color.Transparent)
                ) {
                    // Lined paper background
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 8.dp)
                    ) {
                        repeat(30) { // Create enough lines for scrolling
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(24.dp)
                                    .border(
                                        0.5.dp,
                                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                                        RectangleShape
                                    )
                            )
                        }
                    }

                    // Content TextField
                    val scrollState = rememberLazyListState()
                    val focusRequester = remember { FocusRequester() }

//                    LazyColumn(
//                        state = scrollState,
//                        modifier = Modifier.fillMaxSize()
//                    ) {
//                        item {
                            BasicTextField(
                                value = noteState.content,
                                onValueChange = {
                                    noteState = noteState.copy(content = it)
                                },
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = if (noteState.isBold) FontWeight.Bold else FontWeight.Normal,
                                    fontStyle = if (noteState.isItalic) FontStyle.Italic
                                    else FontStyle.Normal,
                                    fontFamily = FontFamily.Serif,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    lineHeight = 28.sp
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .focusRequester(focusRequester)
                                    .padding(vertical = 8.dp)
                                    .padding(end = 8.dp),
                                decorationBox = { innerTextField ->
                                    if (noteState.content.isEmpty()) {
                                        Text(
                                            text = "Begin writing here...",
                                            fontSize = 20.sp,
                                            fontFamily = FontFamily.Serif,
                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                                            fontStyle = FontStyle.Italic,
                                            lineHeight = 28.sp
                                        )
                                    }
                                    innerTextField()
                                },
                                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
                            )
//                        }
//                    }
                }

                // Footer with stats
                FooterStats(
                    lastEdited = lastEditedFormatted,
                    wordCount = wordCount,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }

        // Dialogs
        if (showCategoryDialog) {
            CategorySelectionDialog(
                onDismiss = { showCategoryDialog = false },
                onCategorySelected = { category ->
                    noteState = noteState.copy(category = category)
                    showCategoryDialog = false
                }
            )
        }
    }
}




