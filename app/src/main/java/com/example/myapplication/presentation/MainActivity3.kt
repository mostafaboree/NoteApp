package com.example.myapplication.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.data.repository.ImP_Repository
import com.example.myapplication.domin.model.Note
import com.example.myapplication.domin.use_case.AddNote
import com.example.myapplication.presentation.ALLNotes.Add_NotePreview
import com.example.myapplication.presentation.ui.theme.MyApplicationTheme
import com.example.myapplication.presentation.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.time.Duration

@AndroidEntryPoint
class MainActivity3 : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val scope= rememberCoroutineScope()
            val context = LocalContext.current
            val snakbarHostState = remember { SnackbarHostState() }

            var state by remember { mutableStateOf("") }

            var listnote by remember { mutableStateOf(emptyList<Note>()) }
            val viewModel = hiltViewModel<NoteViewModel>()


            state=viewModel.state.value
            viewModel.noteList.observe(this) {list->
                listnote=list
                Log.d("TAG", "onCreate: ${list.size}")
            }


            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),snackbarHost = {
                    androidx.compose.material3.SnackbarHost(hostState =snakbarHostState )
                    Text(text = " $state ", style = Typography.bodyLarge)
                }) {  innerPadding ->
                   /* Column(modifier = Modifier.fillMaxSize()) {

                    AddNote{note->
                      viewModel._noteTitle.value=note.title
                        viewModel._noteDescription.value=note.body
                        Log.d("TAG", "onCreate tt: ${note.title} ${note.body}${viewModel._noteTitle.value.toString()} ${viewModel._noteDescription.value.toString()}")
                        viewModel.addNote()
                    }
                  LazyColumn(modifier = Modifier.padding(innerPadding)) {
                      items( listnote){item->
                     //     ItemNote(item)
                      }
                  }
                }*/
Add_NotePreview(modifier = Modifier.padding(innerPadding))
            }
        }
    }}}




@Composable
fun AddNote(onNoteAdded: (Note) -> Unit){
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(value = title,modifier = Modifier.fillMaxWidth(), onValueChange = {
            title =it
        })
        Spacer(modifier =Modifier.height(10.dp))
        Spacer(Modifier.size(16.dp))
        TextField(value = body, modifier = Modifier.fillMaxWidth() ,onValueChange = {
            body=it
        })
        Spacer(Modifier.size(10.dp))
        Button(onClick = {
            onNoteAdded(Note(title,body,System.currentTimeMillis().toString().toString()))
            title=""
            body=""
        }) {
            Text(text = "Add  New Note")
        }
    }
}