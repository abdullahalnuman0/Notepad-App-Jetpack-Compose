package dev.abdullah.noteapp.feature_note.presentation.add_edit_note

// AddNoteScreen.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.abdullah.noteapp.feature_note.domin.util.wordCount
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.AddEditTopBar
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.BottomToolbar
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.CategorySelectionDialog
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.CategorySelector
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.FooterStats
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.LinedNoteInput
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.NoteTitleInput
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


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
    navController: NavController,
    vm: AddEditNoteViewModel = hiltViewModel()
) {


    val uiState by vm.uiState.collectAsStateWithLifecycle()

    // Update last edited time
    val lastEditedFormatted = remember(uiState.content) {
        val sdf = SimpleDateFormat("MMM dd, yyyy · hh:mm a", Locale.getDefault())
        sdf.format(Date())
    }

    LaunchedEffect(Unit) {
//        vm.uiEvent.collLa
    }

    Scaffold(
        topBar = {
            AddEditTopBar(
                onBackClick = { navController.popBackStack() },
                onShare = { },
                onSave = { vm.onEvent(AddEditEvent.SaveNote) }
            )
        },
        bottomBar = {
            BottomToolbar(
                onColorSelect = { color ->
//                    noteState = noteState.copy(colorTag = color)
                },
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
                    category = uiState.category,
                    onClick = { vm.onEvent(AddEditEvent.ToggleCategoryDialog) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Title Input
                NoteTitleInput(
                    title = uiState.title,
                    onValueChange = { vm.onEvent(AddEditEvent.EnteredTitle(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Note Input
                LinedNoteInput(
                    modifier = Modifier.weight(1f),
                    noteContent = uiState.content,
                    onValueChange = { vm.onEvent(AddEditEvent.EnteredContent(it)) }
                )

                // Footer with stats
                FooterStats(
                    lastEdited = lastEditedFormatted,
                    wordCount = uiState.content.wordCount(),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }

        // Dialogs
        if (uiState.showCategoryDialog) {
            CategorySelectionDialog(
                onDismiss = { vm.onEvent(AddEditEvent.ToggleCategoryDialog) },
                onCategorySelected = { vm.onEvent(AddEditEvent.EnteredCategory(it)) }
            )
        }
    }
}




