package com.example.myapplication.presentation.Add_Edit_Note

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TabRow
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.presentation.Reminder
import kotlinx.coroutines.launch
import java.sql.Time

typealias Rmd = R.drawable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddNote(){
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()
    var backgroundColor by remember {mutableStateOf(Color.Black)}
    var showBottomSheet by remember { mutableStateOf(false) }
    var titl = remember { mutableStateOf("") }
    BottomSheetScaffold(
        {

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)){
                Column(modifier = Modifier.align(Alignment.TopCenter)) {
                BottomIcon(modifier = Modifier.fillMaxWidth())
                    Spacer(x =20 )
                SelectColor({ color ->
                    backgroundColor = color
                }, modifier = Modifier.fillMaxWidth())
            }}
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 50.dp,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetBackgroundColor = backgroundColor.copy(alpha = 0.8f),

        ) {innerPadding->

     Box(modifier = Modifier
         .padding(innerPadding)
         .background(brush = Brush.verticalGradient(listOf(backgroundColor, Color.DarkGray)))
         .fillMaxSize()) {

             Row(modifier = Modifier
                 .fillMaxWidth()
                 .padding(10.dp),horizontalArrangement = Arrangement.SpaceBetween) {
    AddIcon(icon =Icons.Default.KeyboardArrowLeft)
    AddIcon(icon =Icons.Default.List)
}
Spacer(x =20 )
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.9f)
        .padding(30.dp)
        .align(
            Alignment.BottomCenter
        ),verticalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
        TextTitle{
            title->
       titl.value=title


        }
        TextBody{
            body->
            titl.value=body
        }

        Text(text = titl.value,color = Color.Blue, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(100.dp)
            .background(Color.White, RoundedCornerShape(20.dp)))
        Reminder()}

        //Image(painter = painterResource(id = Rmd.man), contentDescription ="",modifier = Modifier.size(300.dp))
Button(onClick = {
                 if (sheetState.isCollapsed) {
                     scope.launch {
                         sheetState.expand()
                     }
                 } else {
                     scope.launch {
                         sheetState.collapse()
                     }
                 }
},modifier = Modifier.fillMaxWidth(),shape = RoundedCornerShape(20.dp)){
    Text(text = "Save",color = Color.White)
}
    }
}
}
}

@Composable
private fun BottomIcon(modifier: Modifier) {
    val  color= Color(0xFF508C9B)
    Row(
        modifier = Modifier
            .fillMaxWidth()

            .background(
                Color(0xFF508C9B),
                RoundedCornerShape(20.dp)
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AddIcon(icon = R.drawable.baseline_notification)
        AddIcon(icon = Icons.Default.Add)
        AddIcon(icon = R.drawable.baseline_color_lens_24)
        AddIcon(icon = Rmd.camer)


    }
}

@Composable
fun AddIcon(icon:ImageVector){
    Box(modifier = Modifier.background(Color.Transparent.copy(alpha = 0.2f), CircleShape)){
        Icon(imageVector = icon, contentDescription = "",tint = Color.White,
        modifier = Modifier
            .size(30.dp)
        )


}}
@Composable
fun AddIcon(icon:Int){
    Icon(painter = painterResource(id = icon), contentDescription ="",tint = Color.White,
        modifier = Modifier
            .background(Color.Transparent.copy(alpha = 0.2f), CircleShape)
            .size(30.dp))

}


@Composable
fun TextTitle(header:(String)->Unit){
    var title by remember {mutableStateOf("Title")}
    TextField(value = title, onValueChange = {
        title=it
        header(it)}

    , modifier = Modifier.fillMaxWidth(), textStyle = TextStyle(fontSize = 20.sp,fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold, color = Color.White),)

    Spacer(x =20 )

}
@Composable
fun TextBody(body:(String)->Unit){
    var title by remember {mutableStateOf("Description")}

    TextField(value = title, onValueChange = {title=it
                                             body(it)
                                             }
        , modifier = Modifier.fillMaxWidth(), textStyle = TextStyle(fontSize = 15.sp,fontFamily = FontFamily.Monospace, color = Color.LightGray),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Cyan.copy(alpha = 0.0f)))
    Spacer(x =20 )

}



