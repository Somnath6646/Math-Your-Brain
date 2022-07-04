package com.wenull.mathyourbrain.ui.components

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wenull.mathyourbrain.ui.theme.TimerTextStyle
import kotlinx.coroutines.delay

val hexList = listOf(
    0xFF0BBC00,
    0xFF32C100,
    0xFF81BE00,
    0xFF99CE00,
    0xFFCACE00,
    0xFFDBD200,
    0xFFDB9E00,
    0xFFDB8300,
    0xFFDB7600,
    0xFFDB6900,
    0xFFDB3500
)
var timer_color = Color(hexList[0])

@Composable
fun Timer(
    value: Float,
    currentTime:Long,
    totalTime: Long,
    modifier: Modifier = Modifier,
    onFinish:()-> Unit
) {

    var isTimerRunning by remember {
        mutableStateOf(true)
    }



    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
    ) {


        var n : Float= hexList.size.toFloat();


        if((n/hexList.size)== value) {
            timer_color = Color(hexList.get((n - 1).toInt()))
        }



        Row(
            Modifier
                .background(Color(0xFFECECEC))
                .height(27.dp)
                .fillMaxWidth(1f)) {

        }

        val color = if (value <= 1.0f / hexList.size) {
            Color(hexList.get(hexList.size - 1))
        } else if (value <= 2.0f / hexList.size) {
            Color(hexList.get(hexList.size - 2))
        }else if (value <= 3.0f / hexList.size) {
            Color(hexList.get(hexList.size - 3))
        }else if (value <= 4.0f / hexList.size) {
            Color(hexList.get(hexList.size - 4))
        }else if (value <= 5.0f / hexList.size) {
            Color(hexList.get(hexList.size - 5))
        }else if (value <= 6.0f / hexList.size) {
            Color(hexList.get(hexList.size - 6))
        }else if (value <= 7.0f / hexList.size) {
            Color(hexList.get(hexList.size - 7))
        }else if (value <= 8.0f / hexList.size) {
            Color(hexList.get(hexList.size - 8))
        }else if (value <= 9.0f/ hexList.size) {
            Color(hexList.get(hexList.size - 9))
        }else if (value <= 10.0f / hexList.size) {
            Color(hexList.get(hexList.size - 10))
        } else {
            Color(hexList.get(0))
        }

        val first_part =if((currentTime / 1000)/60 > 10)
            "${(currentTime / 1000L)/60}" else "0"+((currentTime / 1000L)/60).toString()
        val second_part =  if((currentTime / 1000)%60 < 10) "0"+ "${(currentTime / 1000)%60}" else  "${(currentTime / 1000)%60}"

        Row(
            Modifier
                .background(color)
                .height(27.dp)
                .fillMaxWidth(value)) {

        }
        Text(
            text = "$first_part:$second_part",
            style = TimerTextStyle,
            color = if(value <= 1/2f) Color.Black else Color.White,
            modifier= Modifier.align(Alignment.Center)
        )

        if(currentTime <= 0L) {
            onFinish()

            //TimeFinished
        }
    }

}