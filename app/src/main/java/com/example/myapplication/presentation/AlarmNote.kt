package com.example.myapplication.presentation
import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.provider.ContactsContract
import android.provider.ContactsContract.Data
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TabRow
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.Add_Edit_Note.Spacer
import java.util.Calendar
import java.util.Date
//import com.example.myapplication.presentation.Add_Edit_Note.Spacer
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReminderPreview(){
    var selectTap by remember {mutableStateOf(0)}
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(30.dp), verticalArrangement = Arrangement.Center) {
Reminder()

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reminder(){
    var selectTap by remember {
        mutableStateOf(0)
    }
    val timestate = rememberDatePickerState()
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(
            color = Color.White,
            RoundedCornerShape(20.dp)
        )
        .padding(10.dp)) {
        SelectDate {

        }
        Column(modifier = Modifier.padding(10.dp)) {

            Text(text = "Add Reminder", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(x =20 )
            TabRow(selectedTabIndex = selectTap) {
                Text(text = "Time", fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable {
                    selectTap=0
                })
                Text(text = " Place", fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable {
                    selectTap=1
                })
            }
            Spacer(x =20 )
            AnimatedContent(targetState = selectTap, label = "") { tab->
                when(tab){
                    0->{
                        PickTime {

                        }}
                    1->{
                        PickePlace {
                        } }
                }
            }
        }

    }

}
@Composable
fun PickTime(time:()->Unit){
    Column(Modifier.fillMaxWidth()) {
            DataReminder()


    }

}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PickePlace(time:()->Unit){
    Column(Modifier.fillMaxWidth()) {

        repeat(8){
            Text(text = "Add Reminder", fontSize = 20.sp, fontWeight = FontWeight.Black)
        }

    }

}
@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DataReminder(){
    val context=LocalContext.current
    val sdf = SimpleDateFormat("MM / dd")
    var date  by remember { mutableStateOf(sdf.format(Date()))}
    val day=Calendar.getInstance().time
    var selectedDate by  remember { mutableStateOf(false) }

    var menustate  by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth()){
    Column (
        Modifier
            .fillMaxWidth()
            .padding(10.dp)){
        menustate = IcTe(date, menustate,{menustate=!menustate})
        DropdownMenu(expanded = menustate, onDismissRequest = { menustate=false}, modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.7f)) {
            DropdownMenuItem(text = { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text(text = "${date}) ")
                Text(text = "Pick Date")}}, onClick = {
                 selectedDate=!selectedDate
            }) }
        HorizontalDivider(Modifier.fillMaxWidth(),1.dp)}
//        Crossfade(targetState = selectedDate) {it->
//            if (it){
//                SelectDate {
//                 date=   sdf.format(Date(it)).toString()
//                }
//
//
//        }

        }

    }
/*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TimeReminder(){
    var menustate  by remember { mutableStateOf(false) }
    Column (
        Modifier
            .fillMaxWidth()
            .padding(10.dp)){
       IcTe(date = "", menustate =true,{menustate=!menustate} )
        DropdownMenu(expanded = menustate, onDismissRequest = { menustate=false}, modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.7f)) {
            ListItem(text = { Text(text = "Add Reminder") })
            ListItem(text = { Text(text = "Add Reminder") })

        }
        HorizontalDivider(Modifier.fillMaxWidth(),1.dp)
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RepeateReminder(){
    var menustate  by remember { mutableStateOf(false) }
    Column (
        Modifier
            .fillMaxWidth()
            .padding(10.dp)){
        IcTe(date = "", menustate =true ,{menustate=!menustate})

            DropdownMenu(expanded = menustate, onDismissRequest = { menustate=false}, modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(0.7f)) {
                ListItem(text = { Text(text = "Add Reminder") })
                ListItem(text = { Text(text = "Add Reminder") })

            }
        }
        HorizontalDivider(Modifier.fillMaxWidth(),1.dp)
    }
*/



@Composable
private fun IcTe(date: String?, menustate: Boolean,onClic:()->Unit): Boolean {
    var menustate1 = menustate
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${date}",
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Icon(
            imageVector = if (menustate1) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "",
            modifier = Modifier.clickable {onClic()})

    }
    return menustate1
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDate(onDateSelected:(Long)->Unit){
    val state = rememberDatePickerState()
    val date = state.selectedDateMillis.let {
        Calendar.getInstance().apply {
            timeInMillis=it?:System.currentTimeMillis()
        }
    }

    onDateSelected(date.timeInMillis)
    Column (modifier = Modifier.fillMaxWidth()) {
        DatePicker(state = state)
        Row(Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.End) {

                Text(text = "Cancel", fontSize = 18.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily.Serif, color = Color.Blue,modifier = Modifier.clickable {  })

            androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(30.dp))
                Text(text = "Ok",fontSize = 18.sp,fontWeight = FontWeight.Normal,fontFamily = FontFamily.Serif, color = Color.Blue, modifier = Modifier.clickable {  })


        }
    }}
