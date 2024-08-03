package com.example.myapplication.domin.model

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo(name = "title" )val title: String,
    @ColumnInfo(name = "body")   val body :String,
    @ColumnInfo(name = "data")   val data:String,

    @PrimaryKey (autoGenerate = true) val id:Int?=null)
{


    companion object{
        val colorInfo= arrayListOf(Color.GRAY,Color.BLUE,Color.GREEN)
    }
}