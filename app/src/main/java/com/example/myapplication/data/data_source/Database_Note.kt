package com.example.myapplication.data.data_source

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.domin.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class Database_Note:RoomDatabase() {

abstract val getDao:Dao_Note




    }
