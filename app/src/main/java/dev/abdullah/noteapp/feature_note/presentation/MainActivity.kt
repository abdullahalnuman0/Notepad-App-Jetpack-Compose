package dev.abdullah.noteapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import dev.abdullah.noteapp.feature_note.domin.util.NavAddEditNote
import dev.abdullah.noteapp.feature_note.domin.util.NavNoteView
import dev.abdullah.noteapp.feature_note.presentation.add_edit_note.AddNoteScreen
import dev.abdullah.noteapp.feature_note.presentation.notes.NotesScreen
import dev.abdullah.noteapp.ui.theme.NoteAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
//                        startDestination = NavNoteView
                        startDestination = NavAddEditNote(10)
                    ) {

                        composable<NavNoteView> {
                            NotesScreen(navController) {}
                        }

                        composable<NavAddEditNote> {
                            val id = it.toRoute<NavAddEditNote>().id
                            println("-------------\nNote id $id\n-------------")
                            AddNoteScreen(
                                navController = navController
                            )

                        }

                    }
                }
            }
        }
    }
}

