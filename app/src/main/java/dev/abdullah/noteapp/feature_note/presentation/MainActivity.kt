package dev.abdullah.noteapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.abdullah.noteapp.feature_note.domin.util.Screen
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
                        startDestination = Screen.NoteScreen.route
                    ) {

                        composable(Screen.NoteScreen.route) {
                            NotesScreen(){}
//                            AddNoteScreen(
//                                onBackClick = {},
//                                onSaveNote = {}
//                            )
                        }

                        //composable("${Screen.AddEditScreen.route}/{noteId}/{noteColor}") {
                        composable("${Screen.AddEditScreen.route}?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument("noteId") {
                                    type = NavType.IntType
                                    defaultValue = -1

                                },
                                navArgument("noteColor") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }

                            )) {

                            val color = it.arguments?.getInt("noteColor") ?: -1

//                            AddEditNoteScreen(navController = navController, noteColor = color)
                        }


                    }
                }
            }
        }
    }
}

