package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {

    val dao = NoteDatabase.getDatabase(application).getNotesDao()
    val repository:NoteRepository =NoteRepository(dao)

    val allnote:LiveData<List<Note>> =repository.allnotes

    fun deleteNote(note: Note)=viewModelScope.launch (Dispatchers.IO){
        repository.delete(note)
    }
    fun updateNote(note: Note)=viewModelScope.launch (Dispatchers.IO){
        repository.update(note)
    }
    fun addNote(note: Note)=viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }
}