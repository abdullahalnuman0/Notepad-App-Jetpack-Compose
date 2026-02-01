package dev.abdullah.noteapp.feature_note.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                    NoteApp()
                }
            }
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NoteApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigateAnimationTime: Int = 1000 // This is the default time for the animation

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = NavNoteView,
//                        startDestination = NavAddEditNote(10),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(navigateAnimationTime)
                ) + fadeIn(animationSpec = tween(navigateAnimationTime))

            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(navigateAnimationTime)
                ) + fadeOut(animationSpec = tween(navigateAnimationTime))
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(navigateAnimationTime)
                ) + fadeIn(animationSpec = tween(navigateAnimationTime))
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(navigateAnimationTime)
                ) + fadeOut(animationSpec = tween(navigateAnimationTime))
            },
        ) {

            composable<NavNoteView> {
                NotesScreen(navController,this)
            }

            composable<NavAddEditNote> {

                val id = it.toRoute<NavAddEditNote>().id

                AddNoteScreen(
                    navController = navController,
                    this,
                    id = id,
                )

            }
        }
    }
}
