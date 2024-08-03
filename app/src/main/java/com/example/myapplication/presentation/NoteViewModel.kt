package com.example.myapplication.presentation

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domin.model.Note
import com.example.myapplication.domin.use_case.Note_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
@SuppressLint("CheckResult")
@HiltViewModel
class NoteViewModel @Inject constructor(private val useCase: Note_UseCase) : ViewModel() {
     val _noteTitle = MutableLiveData<String>()
    val state = mutableStateOf("")
     val _noteDescription = MutableLiveData<String>()
     val _noteList = MutableLiveData<List<Note>>(emptyList())
    val noteList: LiveData<List<Note>> = _noteList

    init {
        getAllNotes()
       // addNote()

    }

    @SuppressLint("CheckResult")
    fun addNote() {
        _noteTitle.value
        _noteDescription.value
        useCase.addNote(Note(_noteTitle.value.toString(),
            _noteDescription.value.toString(),
            _noteDescription.value.toString())).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe({
                            Log.i("Success","Note added ${_noteTitle.value.toString()}")
        },{
            Log.e("Error",it.message.toString())
        })
    }

    @SuppressLint("CheckResult")
  private  fun getAllNotes():LiveData<List<Note>> {
        useCase.allNote.invoke().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe ({list->
                if (list.isNullOrEmpty()){
                    _noteList.value = emptyList()
                }
                else{
                    _noteList.value = list
                }

        },{
            Log.e("Error",it.message.toString())

            })
return noteList
    }

}