package dev.abdullah.noteapp.feature_note.presentation.notes

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.abdullah.noteapp.feature_note.domin.util.NavAddEditNote
import dev.abdullah.noteapp.feature_note.presentation.notes.components.NewNoteFAB
import dev.abdullah.noteapp.feature_note.presentation.notes.components.NotesGrid
import dev.abdullah.noteapp.feature_note.presentation.notes.components.NotesTopBar
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NotesScreen(
    navController: NavController,
    vm: NotesViewModel = hiltViewModel(),
) {

    val uiState by vm.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {
        vm.uiEvent.collectLatest { event ->
            when (event) {
                is NotesViewModel.UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(event.visuals)
                }
            }
        }
    }

    LaunchedEffect(snackbarHostState.currentSnackbarData) {
        val actionLabel = snackbarHostState.currentSnackbarData?.visuals?.actionLabel?.lowercase()
        when (actionLabel) {
            "pin", "unpin" -> {vm.onEvent(NotesEvent.PinOrUnpin())}
            else -> {}
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
        snackbarHost = { SnackbarHost(snackbarHostState) }
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
                onNoteClick = { navController.navigate(NavAddEditNote(it)) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onEdit = { navController.navigate(NavAddEditNote(it)) },
                onShare = { },
                onPinOrUnpin = { vm.onEvent(NotesEvent.PinOrUnpin(it)) },
                onDelete = { vm.onEvent(NotesEvent.DeleteNote(it)) }
            )
        }
    }

}









