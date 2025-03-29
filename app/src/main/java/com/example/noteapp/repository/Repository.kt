package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.model.Note

class Repository(private val noteDB : NoteDatabase) {
    val noteDAO = noteDB.getNoteDAO()
    suspend fun insertNote(note: Note) {
        noteDAO.insertNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDAO.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDAO.deleteNote(note)
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return noteDAO.getAllNotes()
    }

    fun searchNotes(query : String?) : LiveData<List<Note>> {
        return noteDAO.searchNotes(query)
    }
}