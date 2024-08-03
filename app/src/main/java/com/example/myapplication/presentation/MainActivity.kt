package com.example.myapplication.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.data.data_source.Database_Note
import com.example.myapplication.data.repository.ImP_Repository
import com.example.myapplication.domin.Repository
import com.example.myapplication.domin.model.Note
import com.example.myapplication.domin.use_case.AddNote
import com.example.myapplication.domin.use_case.AllNote
import com.example.myapplication.domin.use_case.Note_UseCase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Observable

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.schedulers.ScheduledRunnable
import io.reactivex.rxjava3.schedulers.Schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

//org.gradle.daemon=true
class MainActivity : AppCompatActivity() {
val context=this.baseContext
    val TAG="MainActivity"
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editId=findViewById<EditText>(R.id.title)
        val editBody=findViewById<EditText>(R.id.body)
        val save= findViewById<FloatingActionButton>(R.id.save)}}
      /* // val note_viewModel :NoteViewModel by viewModel()
       runBlocking {

       }
        save.setOnClickListener {
            val title= editId.text.toString()
            val body=  editBody.text.toString()
            addNote(title,body)
        }

       getAll()



    }

    @SuppressLint("CheckResult")
    fun addNote(title:String, body:String){
        val useCase=AddNote(::repository.invoke())
        useCase.invoke(Note(title,body,"22/10/2021",)).subscribeOn(Schedulers.io()).
        observeOn(Schedulers.io()).subscribe {
            val TAG: String = "MainActivity"
            Log.d(TAG, "frist note complete ")



        }
    }


    fun repository():Repository{
        val db=Database_Note.getInstances(applicationContext)
        val dao=db.getDao()
        val repository=ImP_Repository(dao)
        return repository
    }
    fun recycl(list: List<Note>){
        val recyclerView:RecyclerView=findViewById(R.id.rec)
        val adapter=NoteAdapter(applicationContext,list)
        recyclerView.adapter=adapter
    }
    @SuppressLint("CheckResult")
    fun getAll(){
*//*
      note_viewModel.noteList.observe(this, Observer {
          if(it.isNotEmpty()){
              recycl(it)
          } else{
      Log.e(TAG, "getAll: Empty List ")
          }
      })*//*


      }*/


