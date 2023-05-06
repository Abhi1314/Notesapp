package com.example.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Note(
    @ColumnInfo(name = "title")val noteTitle:String ,
    @ColumnInfo(name = "Description")val noteDescription:String,
    @ColumnInfo(name = "Timestamp")val timeStamp:String)
{
   @PrimaryKey(autoGenerate = true)
   var id=0
}