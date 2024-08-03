package com.example.myapplication.presentation.Add_Edit_Note

import android.Manifest
import android.annotation.SuppressLint

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.myapplication.presentation.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.prefs.Preferences
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

private const val LOG_TAG = "AudioRecordTest"
private const val REQUEST_RECORD_AUDIO_PERMISSION = 200
class AudioRecordActivity : AppCompatActivity() {
    var recorder : MediaRecorder? = null

    private var player: MediaPlayer? = null
    private var fileName = ""
    private var permissionToRecordAccepted = false

    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
        if (!permissionToRecordAccepted) finish()
    }
    private fun onRecord(start: Boolean) = if (start) {
        startRecording()
    } else {
        stopRecording()
    }

   /* private fun onPlay(start: Boolean) = if (start) {
        startPlaying().start()
    } else {
        startPlaying().stop()
        startPlaying().release()
    }

 private fun startPlaying():MediaPlayer {
        player = MediaPlayer().apply {
            try {
                setDataSource("${externalCacheDir?.absolutePath}/recordingz.3gp")
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }

return player as MediaPlayer
    }*/

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }
    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }
    override fun onStop() {
        super.onStop()
        recorder?.release()
        recorder = null
        player?.release()
        player = null
    }


    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalStdlibApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val coroutineScope = rememberCoroutineScope()
            val random=('a'..'z').random()
            fileName = "${externalCacheDir?.absolutePath}/recording$random.3gp"
            var RecordState by remember { mutableStateOf(false) }
            var PlayState by remember { mutableStateOf(false) }

            var crurrentPosition by remember { mutableStateOf(0f) }
            var TimeRecord by remember { mutableStateOf("00:00") }

var oncRecord={
    RecordState=!RecordState
}
var oncPlay={
    PlayState=!PlayState
}

            ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION)
            MyApplicationTheme {
                //crurrentPosition= startPlaying().currentPosition.toFloat()?:0f
              player = MediaPlayer().apply {
                        setDataSource("${externalCacheDir?.absolutePath}/recordingz.3gp")
                        prepare()
                    if (PlayState) player?.start() else player?.stop()
                    player?.setOnCompletionListener {
                        player?.release()
                        player = null
                    }

                    }
                coroutineScope.launch {
                    player?.setOnPreparedListener {
                        crurrentPosition = player?.currentPosition?.toFloat() ?: 0f
                        Log.d("TAG", "onCreate: $crurrentPosition")
                    }
                }
                Log.d("TAG", "onCreate: $crurrentPosition")
                Scaffold(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Column() {
                            AddRecord(RecordState) {
                                oncRecord()
                                onRecord(RecordState)

                            }
                            ItemRecord(position = crurrentPosition, text =TimeRecord , onClick = { oncPlay()
                              //  onPlay(PlayState)
                             }, state =PlayState)
                            Text(text = fileName)
                            Text(text = "Recording")
                            Text(text = "Playing")
                        }
                    } } } }
}

}
@Composable
fun AddRecord(state:Boolean,start:()->Unit){
    Button(onClick = {
       start()
    }) {
        Text(text = if (state) "Start" else "Stop")
}
}

@Composable
fun ItemRecord(position:Float,text:String,onClick:()->Unit,state:Boolean){

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Absolute.SpaceBetween, modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(20))
        .background(Color.Yellow.copy(alpha = .2f))) {
        IconButton(onClick = {onClick()},Modifier.padding(5.dp)) {
            //AddIcon(icon = if (state)  Rmd.baseline_pause else Rmd.play )}
        }
        Spacer(x = 10)
        LinearProgressIndicator(progress = {position}
            , trackColor = Color.LightGray
            , color = Color.Blue
        )
        Spacer(10)

Text(text = text,Modifier.padding(5.dp))

}}