package com.example.myapplication.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.domin.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
 interface Dao_Note {
    @Insert
    fun insert(note: Note):Completable

    @Query("SELECT * FROM note")
    fun getALL():Observable<List<Note>>

    @Query("SELECT * FROM note WHERE id=:id ")
    fun getById(id:Int):Single<Note>

    @Query("DELETE  FROM note WHERE id =:id")
    fun deleteAll(id: Int):Completable

 }