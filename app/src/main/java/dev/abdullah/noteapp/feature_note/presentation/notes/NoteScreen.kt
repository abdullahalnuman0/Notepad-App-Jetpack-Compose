package dev.abdullah.noteapp.feature_note.presentation.notes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.abdullah.noteapp.feature_note.domin.model.Note
import dev.abdullah.noteapp.feature_note.domin.util.NavAddEditNote
import dev.abdullah.noteapp.feature_note.presentation.notes.components.NewNoteFAB
import dev.abdullah.noteapp.feature_note.presentation.notes.components.NotesGrid
import dev.abdullah.noteapp.feature_note.presentation.notes.components.NotesTopBar
import dev.abdullah.noteapp.utils.fakeNotes


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel(),
    notes: List<Note> = fakeNotes,
    onNoteClick: (Int) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            NotesTopBar(
                searchQuery = uiState.searchQuery,
                onSearchChange = { viewModel.onEvent(NotesEvent.Search(it)) },
                selectedSort = uiState.selectedSort,
                onSortChange = { viewModel.onEvent(NotesEvent.Sort(it)) }
            )
        },
        floatingActionButton = {
            NewNoteFAB(onClick = {navController.navigate(NavAddEditNote(1))})
        }
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
            NotesGrid(
                notes = uiState.notes,
                onNoteClick = { noteId -> onNoteClick(noteId) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }

}









