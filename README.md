<div align="center">

# 📝 Notepad App

### A Modern Note-Taking Application Built with Jetpack Compose

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.0-purple.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.0-green.svg?style=flat&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Android](https://img.shields.io/badge/Platform-Android-brightgreen.svg?style=flat&logo=android)](https://www.android.com)
[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)

[Download APK](/screenshots/apk) • [Report Bug](https://github.com/abdullahalnuman0/Notepad-App-Jetpack-Compose/issues) • [Request Feature](https://github.com/abdullahalnuman0/Notepad-App-Jetpack-Compose/issues)

</div>

---

## 📱 Overview

Notepad App is a clean, minimal, and modern Android application designed to help you capture and organize your thoughts effortlessly. Built entirely with **Jetpack Compose**, this app demonstrates modern Android development best practices including MVVM architecture, dependency injection, and local data persistence.

<div align="center">

### ✨ Key Highlights

**Material Design 3** • **Offline-First** • **Lightning Fast** • **Intuitive UI**

</div>

---

## 📸 Screenshots

### 🌞 Light Mode

<div align="center">
  <img src="/screenshots/light_mode_1.jpeg" width="180" alt="Light Mode" />
  <img src="/screenshots/light_mode_2.jpeg" width="180" alt="Light Mode" />
  <img src="/screenshots/light_mode_3.jpeg" width="180" alt="Light Mode" />
  <img src="/screenshots/light_mode_4.jpeg" width="180" alt="Light Mode" />
</div>

### 🌙 Dark Mode

<div align="center">
  <img src="/screenshots/dark_mode_1.jpeg" width="180" alt="Dark Mode" />
  <img src="/screenshots/dark_mode_2.jpeg" width="180" alt="Dark Mode" />
  <img src="/screenshots/dark_mode_3.jpeg" width="180" alt="Dark Mode" />
  <img src="/screenshots/dark_mode_4.jpeg" width="180" alt="Dark Mode" />
</div>

---

## ✨ Features

### Core Functionality
- ✍️ **Create Notes** - Quick and easy note creation with title and content
- ✏️ **Edit Notes** - Seamlessly update your existing notes
- 🗑️ **Delete Notes** - Remove notes you no longer need
- 🔍 **Sort Notes** - Organize notes by date, title, or color
- 🎨 **Color Coding** - Assign colors to notes for better organization
- 💾 **Auto-Save** - All changes are automatically saved locally

### Technical Features
- 📦 **Offline Storage** - All data stored locally using Room Database
- 🚀 **Fast Performance** - Optimized queries and efficient data handling
- 🎯 **Clean Architecture** - MVVM pattern with Repository layer
- 🔄 **Reactive UI** - Real-time updates using StateFlow
- 🌙 **Material Design 3** - Modern UI following latest design guidelines
- 📱 **Responsive Layout** - Adapts to different screen sizes

---

## 🏗️ Architecture

This app follows the **MVVM (Model-View-ViewModel)** architecture pattern with a Repository layer for clean separation of concerns.

```
┌─────────────────────────────────────────────────┐
│                   UI Layer                      │
│         (Jetpack Compose Screens)               │
└────────────────┬────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────┐
│              ViewModel Layer                    │
│        (Business Logic & State)                 │
└────────────────┬────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────┐
│            Repository Layer                     │
│         (Data Access Logic)                     │
└────────────────┬────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────┐
│              Data Layer                         │
│        (Room Database & Entities)               │
└─────────────────────────────────────────────────┘
```

### Architecture Components

- **UI Layer**: Jetpack Compose screens and composables
- **ViewModel**: Manages UI state and handles business logic
- **Repository**: Abstracts data sources and provides a clean API
- **Room Database**: Local SQLite database with type-safe queries
- **Hilt**: Dependency injection framework for better modularity

---

## 🛠️ Tech Stack

### Languages & Frameworks
- **[Kotlin](https://kotlinlang.org/)** - Modern, concise programming language
- **[Jetpack Compose](https://developer.android.com/jetpack/compose)** - Declarative UI toolkit

### Android Jetpack Libraries
- **[Room](https://developer.android.com/training/data-storage/room)** - SQLite abstraction for local data persistence
- **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)** - Lifecycle-aware component for UI state
- **[Hilt](https://developer.android.com/training/dependency-injection/hilt-android)** - Dependency injection framework
- **[Navigation Compose](https://developer.android.com/jetpack/compose/navigation)** - Screen navigation
- **[Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)** - Asynchronous programming
- **[StateFlow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/)** - Reactive state management

### Additional Tools
- **Material Design 3** - Modern UI design system
- **Kotlin DSL** - For Gradle build configuration

---

## 📦 Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/yourpackage/
│   │   │   ├── data/
│   │   │   │   ├── database/
│   │   │   │   │   ├── NoteDao.kt
│   │   │   │   │   └── NoteDatabase.kt
│   │   │   │   ├── model/
│   │   │   │   │   └── Note.kt
│   │   │   │   └── repository/
│   │   │   │       └── NoteRepository.kt
│   │   │   ├── di/
│   │   │   │   └── AppModule.kt
│   │   │   ├── presentation/
│   │   │   │   ├── notes/
│   │   │   │   │   ├── NotesScreen.kt
│   │   │   │   │   └── NotesViewModel.kt
│   │   │   │   ├── add_edit_note/
│   │   │   │   │   ├── AddEditNoteScreen.kt
│   │   │   │   │   └── AddEditNoteViewModel.kt
│   │   │   │   ├── components/
│   │   │   │   └── navigation/
│   │   │   └── MainActivity.kt
│   │   └── res/
│   └── test/
└── build.gradle.kts
```

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog | 2025.2.3 or higher
- Minimum SDK: API 26 (Android 8.0)
- Target SDK: API 36 (Android 16)
- JDK 11 or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/abdullahalnuman0/Notepad-App-Jetpack-Compose.git
   ```

2. **Open the project in Android Studio**
    - Launch Android Studio
    - Select "Open an existing project"
    - Navigate to the cloned repository

3. **Sync Gradle**
    - Android Studio will automatically sync Gradle files
    - Wait for the process to complete

4. **Run the app**
    - Connect an Android device or start an emulator
    - Click the "Run" button or press `Shift + F10`

### Building APK

To build a release APK:

```bash
./gradlew assembleRelease
```

The APK will be generated at:
```
app/build/outputs/apk/release/app-release.apk
```

---

## 💡 Usage

### Creating a Note
1. Tap the floating action button (+) on the home screen
2. Enter a title and content for your note
3. Optionally select a color for your note
4. Tap the save icon to create the note

### Editing a Note
1. Tap on any note from the list
2. Modify the title or content
3. Changes are automatically saved

### Deleting a Note
1. Tap on a note to open it
2. Tap the delete icon
3. Confirm deletion

### Sorting Notes
- Use the sort options at the top of the screen
- Sort by: Title, Date, or Color
- Choose ascending or descending order

---

## 🎨 Design Patterns

### MVVM Architecture
- **Model**: Data classes and Room entities
- **View**: Jetpack Compose UI components
- **ViewModel**: Business logic and state management

### Repository Pattern
- Abstracts data sources from ViewModels
- Provides a clean API for data operations
- Enables easier testing and maintenance

### Dependency Injection (Hilt)
- Constructor injection for better testability
- Scoped dependencies for proper lifecycle management
- Compile-time verification of dependencies

---

## 🧪 Testing

### Unit Tests
Run unit tests with:
```bash
./gradlew test
```

### Instrumentation Tests
Run instrumentation tests with:
```bash
./gradlew connectedAndroidTest
```

---

## 📞 Contact

**Abdullah Al Numan**

- [![GitHub](https://img.shields.io/badge/GitHub-abdullahalnuman0-black?style=flat&logo=github)](https://github.com/abdullahalnuman0)
- [![WhatsApp](https://img.shields.io/badge/WhatsApp-Chat-green?style=flat&logo=whatsapp)](https://wa.me/+8801754155296)
- [![Project](https://img.shields.io/badge/Notepad_Project-blue?style=flat)](https://github.com/abdullahalnuman0/Notepad-App-Jetpack-Compose)
---

## 🙏 Acknowledgments

- [Android Developers](https://developer.android.com/) - Official Android documentation
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose/documentation)
- [Material Design 3](https://m3.material.io/) - Design guidelines
- All contributors and supporters of this project

---

## 🗺️ Roadmap

- [✔] Search functionality for notes
- [ ] Cloud backup and sync
- [✔] Dark/Light theme toggle
- [ ] Rich text formatting
- [✔] Note sharing capabilities
- [ ] Reminders and notifications
- [✔] Categories and tags
- [ ] Export notes (PDF, TXT)
- [ ] Widget support
- [ ] Voice-to-text input

---

## 📊 Statistics

![GitHub repo size](https://img.shields.io/github/repo-size/abdullahalnuman0/Notepad-App-Jetpack-Compose)
![GitHub stars](https://img.shields.io/github/stars/abdullahalnuman0/Notepad-App-Jetpack-Compose?style=social)
![GitHub forks](https://img.shields.io/github/forks/abdullahalnuman0/Notepad-App-Jetpack-Compose?style=social)
![GitHub issues](https://img.shields.io/github/issues/abdullahalnuman0/Notepad-App-Jetpack-Compose)
![GitHub pull requests](https://img.shields.io/github/issues-pr/abdullahalnuman0/Notepad-App-Jetpack-Compose)

---

<div align="center">

### ⭐ If you like this project, please give it a star! ⭐

Made with ❤️ by [Abdullah Al Numan](https://github.com/abdullahalnuman0)

</div>
