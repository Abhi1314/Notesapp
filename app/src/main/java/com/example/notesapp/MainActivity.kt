package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),NoteAdaper.NoteClickInterface {
    private val mainBinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val noteAdapter = NoteAdaper(this, this)
        mainBinding.rvNote.adapter = noteAdapter
        noteViewModel = ViewModelProvider(
            this)[NoteViewModel::class.java]

        noteViewModel.allnote.observe(this) { list ->
            list?.let { noteAdapter.updatelist(it) }
        }
        mainBinding.addQoutes.setOnClickListener {
            val intent= Intent(this@MainActivity,AddEditNotesActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDeleteIconClick(note: Note) {
        noteViewModel.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle} Deleted",Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(note: Note) {
        val intent= Intent(this@MainActivity,AddEditNotesActivity::class.java)
        intent.putExtra("NoteType","Edit")
        intent.putExtra("NoteDescription",note.noteDescription)
        intent.putExtra("NoteTitle",note.noteTitle)
        intent.putExtra("NoteID",note.id)
        startActivity(intent)
        finish()
    }


}