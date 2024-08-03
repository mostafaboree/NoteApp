package com.example.myapplication.domin

import com.example.myapplication.domin.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getALL():Observable<List<Note>>
    fun getById(id:Int):Single<Note>
    fun add(note: Note):Completable
    fun delete(id:Int):Completable
}