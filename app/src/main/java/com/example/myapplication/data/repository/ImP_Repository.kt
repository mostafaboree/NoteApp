package com.example.myapplication.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.myapplication.data.data_source.Dao_Note
import com.example.myapplication.domin.Repository
import com.example.myapplication.domin.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class ImP_Repository (val dao:Dao_Note):Repository {
    @SuppressLint("CheckResult")
    override fun getALL(): Observable<List<Note>> {
        val TAG = "ImP_Repository"
        dao.getALL().subscribe { list ->
            Log.d(TAG, "getALL: ${list.size}")
        }

        return try {
            dao.getALL()
        } catch (e:Exception){
            Observable.error(e)

        }
    }
    override fun getById(id: Int): Single<Note> {
        return dao.getById(id)
    }

    override fun add(note: Note):Completable {
return dao.insert(note)
    }

    override fun delete(id: Int): Completable {
        return dao.deleteAll(id)
    }
}