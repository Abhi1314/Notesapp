package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityAddEditNotesBinding
import java.text.SimpleDateFormat
import java.util.*

class AddEditNotesActivity : AppCompatActivity() {
    private val addEditNotesBinding: ActivityAddEditNotesBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_add_edit_notes)
    }
    lateinit var noteViewModel: NoteViewModel
    var noteID = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
        val notetype = intent.getStringExtra("NoteType")
        if (notetype.equals("Edit")) {
            val notetitle = intent.getStringExtra("NoteTitle")
            val notedesc = intent.getStringExtra("NoteDescription")
            noteID = intent.getIntExtra("", -1)
            addEditNotesBinding.click.text = "Update Note"
            addEditNotesBinding.edNoteTitle.setText(notetitle)
            addEditNotesBinding.edDescription.setText(notedesc)
        } else {
            addEditNotesBinding.click.text = "Save Note"
        }
        addEditNotesBinding.click.setOnClickListener {
            Log.d("click", "mffk")
            val Notetitle = addEditNotesBinding.edNoteTitle.text.toString()
            val Notedesc = addEditNotesBinding.edDescription.text.toString()
            if (notetype.equals("Edit")) {
                if (Notetitle.isNotEmpty() && Notedesc.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM,yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val updatenote = Note(Notetitle, Notedesc, currentDate)
                    updatenote.id = noteID
                    noteViewModel.updateNote(updatenote)
                    Toast.makeText(this, "note updated", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (Notetitle.isNotEmpty() && Notedesc.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM,yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    noteViewModel.addNote(Note(Notetitle, Notedesc, currentDate))
                    Toast.makeText(this, "note added", Toast.LENGTH_SHORT).show()
                }

            }
            startActivity(Intent(this@AddEditNotesActivity, MainActivity::class.java))
            finish()


        }
    }
}

