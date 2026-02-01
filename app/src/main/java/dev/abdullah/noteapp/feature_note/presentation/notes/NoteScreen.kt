package dev.abdullah.noteapp.feature_note.presentation.notes

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.abdullah.noteapp.feature_note.domin.util.NavAddEditNote
import dev.abdullah.noteapp.feature_note.domin.util.shareText
import dev.abdullah.noteapp.feature_note.presentation.notes.components.EmptyNotes
import dev.abdullah.noteapp.feature_note.presentation.notes.components.NewNoteFAB
import dev.abdullah.noteapp.feature_note.presentation.notes.components.NotesGrid
import dev.abdullah.noteapp.feature_note.presentation.notes.components.NotesTopBar
import kotlinx.coroutines.flow.collectLatest


@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun SharedTransitionScope.NotesScreen(
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    vm: NotesViewModel = hiltViewModel(),
) {

    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {
        vm.uiEvent.collectLatest { event ->
            when (event) {
                is NotesViewModel.UiEvent.ShowSnackBar -> {
                    val result = snackbarHostState.showSnackbar(event.visuals)

                    if (result == SnackbarResult.ActionPerformed) {

                        when (event.visuals.actionLabel!!.lowercase()) {
                            "pin", "unpin" -> {
                                vm.onEvent(NotesEvent.PinOrUnpin())
                            }

                            "undo" -> {
                                vm.onEvent(NotesEvent.RestoreNote)
                            }
                        }
                    }
                }

                is NotesViewModel.UiEvent.ShareNote -> {
                    val note = event.note
                    val sharedText = "`${note.category}`\n" +
                            if (note.title.isNotBlank()) "*${note.title}*\n_${note.content.trim()}_" else "_${note.content.trim()}_";
                    shareText(context, sharedText)
                }
            }
        }
    }


    Scaffold(
        topBar = {
            NotesTopBar(
                searchQuery = uiState.searchQuery,
                onSearchChange = { vm.onEvent(NotesEvent.Search(it)) },
                selectedSort = uiState.selectedSort,
                onSortChange = { vm.onEvent(NotesEvent.Sort(it)) }
            )
        },
        floatingActionButton = {
            NewNoteFAB(onClick = { navController.navigate(NavAddEditNote(null)) })
        },
        snackbarHost = { SnackbarHost(snackbarHostState, Modifier.zIndex(200f)) }
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
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.background,
                                MaterialTheme.colorScheme.background.copy(alpha = 0.7f)
                            ),
                            radius = 500f
                        )
                    )
            )

            // Notes Grid
            if (uiState.notes.isEmpty())
                EmptyNotes()
            else
                NotesGrid(
                    notes = uiState.notes,
                    onNoteClick = { navController.navigate(NavAddEditNote(it)) },
                    animatedVisibilityScope = animatedVisibilityScope,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    onEdit = { navController.navigate(NavAddEditNote(it)) },
                    onShare = { vm.onEvent(NotesEvent.ShareNote(it)) },
                    onPinOrUnpin = { vm.onEvent(NotesEvent.PinOrUnpin(it)) },
                    onDelete = { vm.onEvent(NotesEvent.DeleteNote(it)) }
                )
        }
    }

}









