package com.example.myapplication.presentation.Add_Edit_Note

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SelectColor(OnColorSelected:(color: Color)->Unit, modifier: Modifier = Modifier) {
    val colors = listOf(
        Color.White,
        Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan, Color.Magenta, Color.Gray)
    LazyRow(modifier = Modifier.clickable {  }) {
        items(colors){color->
            ColorBox(color = color,selectedColor = OnColorSelected)
        }

    }
}
@Composable
fun ColorBox(color: Color, modifier: Modifier = Modifier, selectedColor:(color: Color)->Unit){

    var state by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                state = !state
                if (state) {
                    selectedColor(color)

                } else {
                    selectedColor(Color.Black)
                }
            }
            .size(45.dp)
            .background(color, CircleShape)
            .border(1.dp, if (state) Color.Blue else Color.LightGray, CircleShape)){
        Crossfade(targetState = state, modifier = modifier.align(Alignment.Center)) {
            if (it) {
                Icon(imageVector = Icons.Default.Check, contentDescription ="",tint = Color(0xFF000000),
                    modifier = Modifier.size(30.dp))
            }else{

            }



        }
    }
}

@Composable
fun Spacer(x:Int) {
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(x.dp))
}