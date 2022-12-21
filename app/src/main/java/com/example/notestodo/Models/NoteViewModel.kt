package com.example.notestodo.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notestodo.Database.NoteDao
import com.example.notestodo.Database.NoteDatabase
import com.example.notestodo.Database.notesRepository

class NoteViewModel(application: Application):AndroidViewModel(application) {
    private val repository = notesRepository

    val allNotes: LiveData<List<Note>>

    init {
        val dao =NoteDatabase.getDatabase(application).getNoteDao()
        repository=notesRepository(dao)
    }

}