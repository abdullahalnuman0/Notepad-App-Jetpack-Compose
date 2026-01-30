package dev.abdullah.noteapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.abdullah.noteapp.feature_note.data.data_source.NoteDatabase
import dev.abdullah.noteapp.feature_note.data.repository.NoteRepositoryImpl
import dev.abdullah.noteapp.feature_note.domin.repository.NoteRepository
import dev.abdullah.noteapp.feature_note.domin.use_case.AddNoteUseCase
import dev.abdullah.noteapp.feature_note.domin.use_case.DeleteNoteUseCase
import dev.abdullah.noteapp.feature_note.domin.use_case.GetNoteUseCase
import dev.abdullah.noteapp.feature_note.domin.use_case.GetNotesUseCase
import dev.abdullah.noteapp.feature_note.domin.use_case.NoteUseCases
import jakarta.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
             context.applicationContext,
             NoteDatabase::class.java,
             NoteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            observeNotesUseCase = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )
    }

}