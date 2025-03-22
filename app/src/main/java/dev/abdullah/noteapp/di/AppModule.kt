package dev.abdullah.noteapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.abdullah.noteapp.feature_note.data.data_source.NoteDatabase
import dev.abdullah.noteapp.feature_note.data.repository.NoteRepositoryImpl
import dev.abdullah.noteapp.feature_note.domin.repository.NoteRepository
import dev.abdullah.noteapp.feature_note.domin.use_case.AddNoteUseCase
import dev.abdullah.noteapp.feature_note.domin.use_case.DeleteNoteUseCase
import dev.abdullah.noteapp.feature_note.domin.use_case.GetNoteUseCase
import dev.abdullah.noteapp.feature_note.domin.use_case.GetNotesUseCase
import dev.abdullah.noteapp.feature_note.domin.use_case.NoteUseCases
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
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
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )
    }

}