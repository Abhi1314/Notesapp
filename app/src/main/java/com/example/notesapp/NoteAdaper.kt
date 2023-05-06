package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdaper(val context:Context,
                 val noteClickInterface: NoteClickInterface) : RecyclerView.Adapter<NoteAdaper.ViewHolder>() {
    private val allnotes=ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdaper.ViewHolder {
       val view:View=LayoutInflater.from(parent.context).inflate(R.layout.notelist,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdaper.ViewHolder, position: Int) {
        holder.notetitle.text = allnotes[position].noteTitle
        holder.timestamp.text = "Last Updated :"+allnotes[position].timeStamp
        holder.notedescr.text = allnotes[position].noteDescription
        holder.delete.setOnClickListener {
            noteClickInterface.onDeleteIconClick(allnotes[position])
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allnotes[position])
        }
    }

    override fun getItemCount(): Int {
     return allnotes.size
    }
    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       val notetitle=itemView.findViewById<TextView>(R.id.edNoteTitle)
       val timestamp=itemView.findViewById<TextView>(R.id.edTimestamp)
       val notedescr=itemView.findViewById<TextView>(R.id.edNotedescr)
       val delete=itemView.findViewById<ImageView>(R.id.btnDelete)
    }
    fun updatelist(newlist:List<Note>){
        allnotes.clear()
        allnotes.addAll(newlist)
        notifyDataSetChanged()
    }
    interface NoteClickInterface{
        fun onNoteClick(note: Note)
        fun onDeleteIconClick(note: Note)
    }
}