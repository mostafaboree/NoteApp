package com.example.myapplication.presentation.ALLNotes

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.OvalShape
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Spring.DampingRatioMediumBouncy
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults.ContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrixColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.transition.Visibility
import com.example.myapplication.domin.model.Note
import com.example.myapplication.presentation.Add_Edit_Note.Rmd
import com.example.myapplication.presentation.ui.theme.MyApplicationTheme
import java.util.Collections.list
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
        floatingActionButton = {
            FloatingActionButtonAdd()
        }) {innerPadding->
Add_NotePreview(modifier = Modifier.padding(innerPadding))}}

@Composable
fun FloatingActionButtonAdd() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Default.Create,"",)
    }

}


@OptIn(ExperimentalFoundationApi::class)
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Add_NotePreview(modifier: Modifier) {
    var NoteList  by remember {mutableStateOf(listMoke)}
    MyApplicationTheme(dynamicColor = true) {
        val randomColor= Color.Yellow.copy(alpha = 0.5f)
        val OnSelected: () -> Unit = {
          NoteList=  NoteList.reversed()
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                OnSelected()
            }
            .background(brush = Brush.verticalGradient(listOf(Color.Black, Color.DarkGray)))) {
            AppBar()
            TypeNote(OnSelected)


LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(minSize = 170.dp), content = {

    items(NoteList, key = { it.id!! }) { item ->
        ItemNote(item = item,randomColor,Modifier.animateItemPlacement(
           spring(dampingRatio = DampingRatioMediumBouncy,stiffness = 100f)

        ))


    }
})
}}}

@Composable
fun ItemNote(item: Note,noteColor: Color,Modifier: Modifier ) {
    var visibility by remember { mutableStateOf(true) }
    /*AnimatedVisibility(visible = visibility,
        enter = fadeIn(tween(1000))+ scaleIn(tween(1000)),
        exit = fadeOut(tween(1000))+ scaleOut(tween(1000)),
    ) {*/

    Column(modifier = Modifier
        .clickable {
            visibility = !visibility
        }

        .clip(shape = RoundedCornerShape(30.dp))
        .background(color = noteColor)//(brush = Brush.verticalGradient(listOf(randomColor)))
        .border(
            width = 2.dp, color = Color.Black, shape = RoundedCornerShape(30.dp)
        )) {
        Text(text = "${item.title}", modifier = Modifier.padding(10.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)
        Text(text = "${item.body}", modifier = Modifier.padding(10.dp), fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            maxLines = 5,

        )

    }}

@Composable
fun AppBar() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.LightGray,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("H")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("ello ,")
                }

            }, modifier = Modifier.padding(10.dp)
        )
        Image(painter = painterResource(id = Rmd.notepad), contentDescription ="",
            modifier = Modifier
                .size(70.dp)
                .offset(0.dp, 0.dp)
                .clip(CircleShape)
                .shadow(5.dp, CircleShape))
    }
    Text(text = "My Notes", modifier = Modifier.padding(10.dp) ,fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = Color.White.copy(alpha = 0.5f),
        fontFamily = FontFamily.Serif,
        letterSpacing = 2.sp)

}


@SuppressLint("RememberReturnType")
@Composable
fun TypeNote(onSelected: () -> Unit) {
    var selected  by remember{ mutableStateOf(false) }
    val listType= listOf("#Work","#Personal","#Health","#Study","#Travel","#Coding","#other")
    
    LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = ContentPadding) {
        items(listType) { item ->
            ItemSelect(item,onSelected)

    }

}}


@Composable
private fun ItemSelect(item: String,onSelect: () -> Unit) {
    var selected  by remember{ mutableStateOf(false) }
    Box(
        modifier = Modifier
            .clickable {
                onSelect()
            }
            .padding(5.dp)
            .border(
                BorderStroke(
                    2.dp,
                    brush = Brush.verticalGradient(
                        if (selected) listOf(
                            Color.Magenta,
                            Color.White
                        ) else listOf(Color.LightGray, Color.White)
                    )
                ), shape = RoundedCornerShape(20.dp)
            )
    ) {
        Crossfade(targetState = selected) {
            if (it) {
                Text(text = item,
                    modifier = Modifier
                        .clickable {
                            selected = !selected

                        }
                        .background(
                            brush = Brush.verticalGradient(listOf(Color.Transparent, Color.White)),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 18.sp)
            } else

            {
                Text(text = item,
                    modifier = Modifier
                        .clickable {
                            selected = !selected
                        }

                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                    color = Color.LightGray,
                    fontSize = 18.sp)

            }


        }
    }
}

sealed class  OrderType(val orderType: String){
    object Ascending: OrderType("Ascending")
    object Descending: OrderType("Descending")
}




