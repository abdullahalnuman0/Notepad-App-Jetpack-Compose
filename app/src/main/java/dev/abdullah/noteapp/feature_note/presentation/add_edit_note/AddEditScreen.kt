package dev.abdullah.noteapp.feature_note.presentation.add_edit_note

// AddNoteScreen.kt
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.abdullah.noteapp.feature_note.domin.util.formatDate
import dev.abdullah.noteapp.feature_note.domin.util.shareText
import dev.abdullah.noteapp.feature_note.domin.util.wordCount
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.AddEditTopBar
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.BottomToolbar
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.CategorySelectionDialog
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.CategorySelector
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.FooterStats
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.LinedNoteInput
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.components.NoteTitleInput
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AddNoteScreen(
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    id: Int?,
    vm: AddEditNoteViewModel = hiltViewModel()
) {

    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope ()


    var isNoteGetUsingThatId by remember { mutableStateOf(id == null) }
    LaunchedEffect(isNoteGetUsingThatId) {
        if (id != null && !isNoteGetUsingThatId) vm.onEvent(AddEditEvent.GetOldNote(id))
    }


    LaunchedEffect(Unit) {
        vm.uiEvent.collectLatest { event ->
            when (event) {
                AddEditNoteViewModel.UiEvent.Back -> navController.popBackStack()
                is AddEditNoteViewModel.UiEvent.ShowSnackBar -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            AddEditTopBar(
                onBackClick = { vm.onEvent(AddEditEvent.SaveNote) },
                onShare = {
                    val sharedText = "`${uiState.category ?: "Other"}`\n" +
                            if (uiState.title.isNotBlank()) "*${uiState.title}*\n_${uiState.content.trim()}_" else "_${uiState.content.trim()}_";
                    shareText(context, sharedText)
                },
                onSave = { vm.onEvent(AddEditEvent.SaveNote) }
            )
        },
        bottomBar = {

            BottomToolbar(
                onAllButtonCLick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Working on it",
                            withDismissAction = true
                        )
                    }
                }
            )

        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                    id = id ?: -1,
                    animatedVisibilityScope = animatedVisibilityScope,
                    onClick = { vm.onEvent(AddEditEvent.ToggleCategoryDialog) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Title Input
                NoteTitleInput(
                    title = uiState.title,
                    id = id ?: -1,
                    animatedVisibilityScope = animatedVisibilityScope,
                    onValueChange = { vm.onEvent(AddEditEvent.EnteredTitle(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Note Input
                LinedNoteInput(
                    modifier = Modifier.weight(1f),
                    noteContent = uiState.content,
                    id = id ?: -1,
                    animatedVisibilityScope = animatedVisibilityScope,
                    onValueChange = { vm.onEvent(AddEditEvent.EnteredContent(it)) }
                )

                // Footer with stats
                FooterStats(
                    lastEdited = uiState.lastUpdateTime.formatDate(),
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

    BackHandler {
        vm.onEvent(AddEditEvent.SaveNote)
    }
}




