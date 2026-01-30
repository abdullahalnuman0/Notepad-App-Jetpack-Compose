package dev.abdullah.noteapp.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import dev.abdullah.noteapp.feature_note.domin.model.Note
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// TODO: Helper functions
val LightCategoryTagColors = mapOf(
    "work" to Color(0xFF2F6F4E),       // Dark green
    "personal" to Color(0xFF8A5A1F),   // Dark warm brown
    "ideas" to Color(0xFF5E3B8C),      // Dark purple
    "to-do" to Color(0xFF1F5F6B),      // Dark cyan
    "learning" to Color(0xFF2C3E7A),   // Dark blue
    "other" to Color(0xFF4A4A4A)       // Dark gray
)
val DarkCategoryTagColors = mapOf(
    "work" to Color(0xFF9AD6B4),       // Light green tint
    "personal" to Color(0xFFE6C28A),   // Warm light brown
    "ideas" to Color(0xFFCBB6F0),      // Light purple
    "to-do" to Color(0xFF9FD6E0),      // Light cyan
    "learning" to Color(0xFFAFC1FF),   // Soft blue
    "other" to Color(0xFFBDBDBD)       // Light gray
)

@Composable
fun String.getCategoryTextColor(): Color {
    val isDarkTheme = isSystemInDarkTheme()
    val key = this.lowercase()

    return if (isDarkTheme)
        DarkCategoryTagColors[key] ?: Color.White
    else
        LightCategoryTagColors[key] ?: Color.Black

}


val LightCategoryBgColors = mapOf(
    "work" to Color(0xFFE6F4EA),    // Soft green – focus & productivity
    "personal" to Color(0xFFFFF4E0),    // Warm cream – personal & calm
    "ideas" to Color(0xFFF0E6FA),   // Light purple – creativity & ideas
    "to-do" to Color(0xFFE0F2F5),   // Soft cyan – tasks & action
    "learning" to Color(0xFFEAF1FF),    // Light blue – learning & clarity
    "other" to Color(0xFFF2F2F2)    // Neutral grey – misc / other
)
val DarkCategoryBgColors = mapOf(
    "work" to Color(0xFF1F3D2B),       // Dark green
    "personal" to Color(0xFF3D2F1B),   // Dark warm brown
    "ideas" to Color(0xFF2E1F3D),      // Dark purple
    "to-do" to Color(0xFF1E3A40),      // Dark cyan
    "learning" to Color(0xFF1C2747),   // Dark blue
    "other" to Color(0xFF2B2B2B)       // Neutral dark gray
)


@Composable
fun String.getCategoryBackgroundColor(): Color {

    val isDarkTheme = isSystemInDarkTheme()
    val key = this.lowercase()

    return if (isDarkTheme)
        DarkCategoryBgColors[key] ?: Color(0xFF121212)
    else
        LightCategoryBgColors[key] ?: Color.White


}


fun formatTimeAgo(dateLong: Long): String {
    val date = Date(dateLong)
    val now = Date()
    val diff = now.time - date.time

    return when {
        diff < 60000 -> "Just now"
        diff < 3600000 -> "${diff / 60000}m ago"
        diff < 86400000 -> "${diff / 3600000}h ago"
        diff < 604800000 -> "${diff / 86400000}d ago"
        else -> {
            val sdf = SimpleDateFormat("MMM d", Locale.getDefault())
            sdf.format(date)
        }
    }
}

// Sample data
fun daysAgo(days: Int): Long {
    return Calendar.getInstance().apply {
        add(Calendar.DAY_OF_YEAR, -days)
    }.time.time
}

val fakeNotes = listOf(

    // ---------- SHORT (20–50 words) ----------
    Note(
        id = 1,
        title = "Morning Thoughts",
        category = "Personal",
        lastUpdated = daysAgo(0),
        content = "Woke up early today and felt more focused than usual.",
    ),

    Note(
        id = 2,
        title = "Work Reminder",
        category = "Work",
        lastUpdated = daysAgo(1),
        content = "Finish pending bug fixes, update documentation, and push changes before end of the day."
    ),

    Note(
        id = 3,
        title = "Quick Idea",
        category = "Ideas",
        lastUpdated = daysAgo(2),
        content = "Add dark mode scheduling based on system time for better user experience."
    ),

    Note(
        id = 4,
        title = "Shopping List",
        category = "To-Do",
        lastUpdated = daysAgo(3),
        content = "Buy notebook, pen, USB cable, and backup hard drive."
    ),

    Note(
        id = 5,
        title = "Learning Reminder",
        category = "Learning",
        lastUpdated = daysAgo(4),
        content = "Revise Kotlin coroutines and practice flow examples."
    ),

    Note(
        id = 6,
        title = "Random Note",
        category = "Other",
        lastUpdated = daysAgo(5),
        content = "Sometimes taking a break actually improves productivity."
    ),

    // ---------- MEDIUM (100–150 words) ----------
    Note(
        id = 7,
        title = "Personal Improvement Plan",
        category = "Personal",
        lastUpdated = daysAgo(6),
        content = """
        Over the next few weeks, I want to focus on building better habits.
        Consistency matters more than intensity, so even small daily progress
        should be considered a success. I plan to wake up earlier, reduce
        unnecessary screen time, and spend at least one hour learning something
        new every day. Tracking habits weekly instead of daily may help reduce
        pressure and stress. I also want to focus more on physical health by
        exercising regularly and maintaining a balanced diet. These small
        improvements can compound into long-term positive changes.
        """.trimIndent()
    ),

    Note(
        id = 8,
        title = "Work Weekly Summary",
        category = "Work",
        lastUpdated = daysAgo(7),
        content = """
        This week was mostly focused on fixing existing issues rather than
        adding new features.
        """.trimIndent()
    ),

    Note(
        id = 9,
        title = "App Feature Brainstorm",
        category = "Ideas",
        lastUpdated = daysAgo(8),
        content = """
        I have been thinking about improving note organization inside the app.
        Smart filters based on usage frequency could help users find important
        notes faster. Another idea is to allow quick pinning and color tagging
        directly from the list view. Offline-first design with background sync
        would improve reliability. These features should be implemented step
        by step after validating performance impact.
        """.trimIndent()
    ),

    Note(
        id = 10,
        title = "Daily Task Strategy",
        category = "To-Do",
        lastUpdated = daysAgo(9),
        content = """
        Planning tasks at the beginning of the day reduces decision fatigue.
        I should focus on completing the most important tasks first instead
        of trying to do everything at once. Leaving buffer time helps handle
        unexpected work. Reviewing tasks at the end of the day allows better
        planning for tomorrow and builds consistency.
        """.trimIndent()
    ),

    Note(
        id = 11,
        title = "Learning Programming Effectively",
        category = "Learning",
        lastUpdated = daysAgo(10),
        content = """
        Learning programming requires active practice rather than passive
        watching. Writing code daily helps solidify concepts and improve
        problem-solving skills. Making small projects exposes real-world
        challenges that tutorials often miss. Keeping notes on mistakes
        and solutions helps avoid repeating errors. Consistency is more
        important than long study sessions.
        """.trimIndent()
    ),

    Note(
        id = 12,
        title = "Unstructured Thoughts",
        category = "Other",
        lastUpdated = daysAgo(11),
        content = """
        Writing random thoughts without structure helps clear the mind.
        Not every note needs a clear purpose. Over time, these notes can
        reveal patterns in thinking and personal growth.
        """.trimIndent()
    ),

    // ---------- MIX AGAIN ----------
    Note(
        id = 13,
        title = "Short Personal Note",
        category = "Personal",
        lastUpdated = daysAgo(12),
        content = "Felt productive today after organizing my workspace."
    ),

    Note(
        id = 14,
        title = "Work Checklist",
        category = "Work",
        lastUpdated = daysAgo(13),
        content = "Review code, test features, update README, deploy build."
    ),

    Note(
        id = 15,
        title = "Idea Dump",
        category = "Ideas",
        lastUpdated = daysAgo(14),
        content = "Voice note support could be useful for quick thoughts."
    ),

    Note(
        id = 16,
        title = "To-Do Weekend",
        category = "To-Do",
        lastUpdated = daysAgo(15),
        content = "Clean desk, backup files, review weekly goals."
    ),

    Note(
        id = 17,
        title = "Learning Reflection",
        category = "Learning",
        lastUpdated = daysAgo(16),
        content = "Understanding fundamentals deeply makes advanced topics easier."
    ),

    Note(
        id = 18,
        title = "Other Note",
        category = "Other",
        lastUpdated = daysAgo(17),
        content = "Small improvements every day lead to big results."
    ),

    Note(
        id = 19,
        title = "Personal Motivation",
        category = "Personal",
        lastUpdated = daysAgo(18),
        content = """
        Motivation comes and goes, but discipline stays. Relying only on
        motivation leads to inconsistency. Building systems and routines
        ensures progress even on low-energy days.
        """.trimIndent()
    ),

    Note(
        id = 20,
        title = "Work Reflection",
        category = "Work",
        lastUpdated = daysAgo(20),
        content = """
        Taking time to review completed work helps identify areas of
        improvement. Quality matters more than speed in the long run.
        """.trimIndent()
    )
)
