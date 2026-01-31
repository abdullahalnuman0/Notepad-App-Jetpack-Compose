package dev.abdullah.noteapp.feature_note.data.repository

import dev.abdullah.noteapp.feature_note.data.data_source.NoteDao
import dev.abdullah.noteapp.feature_note.domin.model.Note
import dev.abdullah.noteapp.feature_note.domin.repository.NoteRepository
import dev.abdullah.noteapp.feature_note.domin.util.fakeNotes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {
    override fun observeNotes(): Flow<List<Note>> = dao.observeNotes()
//    override fun observeNotes(): Flow<List<Note>> = flow { emit(fakeNotes) }


    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}