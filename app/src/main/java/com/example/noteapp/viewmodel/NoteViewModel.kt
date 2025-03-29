package com.example.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.model.Note
import com.example.noteapp.repository.Repository
import kotlinx.coroutines.launch

class NoteViewModel(
    app : Application,
    private val noteRepository: Repository) : AndroidViewModel(app) {


    fun addNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun getAll() : LiveData<List<Note>>{
        return noteRepository.getAllNotes()
    }

    fun searchNote(query : String?) : LiveData<List<Note>> {
        return noteRepository.searchNotes(query)
    }
}