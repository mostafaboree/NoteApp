package com.example.myapplication.presentation.Add_Edit_Note

import androidx.lifecycle.ViewModel
import com.example.myapplication.domin.model.Note
import com.example.myapplication.domin.use_case.Note_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel@Inject constructor(private val useCases: Note_UseCase):ViewModel() {
    val title = MutableStateFlow("")
    private val _title = title
    val content = MutableStateFlow("")
    private val _content = content
    val date = MutableStateFlow("")
    private val _date = date
    val color = MutableStateFlow("")
    private val _time = color


    fun addNote() {
    }



}