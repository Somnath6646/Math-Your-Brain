package com.wenull.mathyourbrain.ui.components.gamescreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.wenull.mathyourbrain.ui.theme.CTAButtonTextStyle
import com.wenull.mathyourbrain.ui.theme.*

@Composable
fun GameCompleted(newGame: ()-> Unit, leave: () -> Unit, newRating: Int, correctQuestions: Int, totalScore: Int, completedTime: Long, scoredPoints: Int){

    Dialog(
        onDismissRequest = {  },
        properties = DialogProperties()
    ) {
        Column(
            Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .padding(25.dp)) {

            Column(Modifier.fillMaxWidth(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "NEW RATING",
                    style = NewRatingOverlineTextStyle,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(bottom = 5.dp),
                    textAlign = TextAlign.Center
                )

                Text(
                    "$newRating", style = NewRatingTextStyle, modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(bottom = 20.dp), textAlign = TextAlign.Center
                )
            }
            Row(
                Modifier
                    .fillMaxWidth(1f)
                    .padding(bottom = 25.dp), horizontalArrangement = Arrangement.SpaceBetween){
                Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Correct",
                        style = ScoreOverlineTextStyle,
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        "$correctQuestions/20", style = ScoreTextStyle, modifier = Modifier
                        , textAlign = TextAlign.Center
                    )
                }
                Column(Modifier.fillMaxWidth(1/3f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Time",
                        style = ScoreOverlineTextStyle,
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )

                    Log.i("Hey 12456", completedTime.toString())

                    val first_part =if((completedTime)/60 > 10)
                        "${(completedTime)/60}" else "0"+((completedTime)/60).toString()
                    val second_part =  if((completedTime )%60 < 10) "0"+ "${(completedTime)%60}" else  "${(completedTime)%60}"

                    val strTime ="$first_part:$second_part"
                    Text(
                        strTime, style = ScoreTextStyle, modifier = Modifier, textAlign = TextAlign.Center
                    )
                }
                Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Score",
                        style = ScoreOverlineTextStyle,
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        "$scoredPoints/$totalScore", style = ScoreTextStyle, modifier = Modifier
                        , textAlign = TextAlign.Center
                    )
                }
            }







            Column() {
                val interactionSource = remember { MutableInteractionSource() }


                Surface(
                    modifier = Modifier

                        .padding(bottom = 12.dp)
                        .fillMaxWidth(1f)
                        .clip(RoundedCornerShape(10.dp)),
                    elevation = FloatingActionButtonDefaults.elevation()
                        .elevation(interactionSource).value,
                ) {
                    Box(modifier = Modifier

                        .clickable { leave() }
                        .background(Color(0xFFF8F8F8))
                        .padding(10.dp), contentAlignment = Alignment.Center) {
                        Text(text = "Leave", color = Color.Black, style = CTAButtonTextStyle)
                    }
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .clip(RoundedCornerShape(10.dp)),
                    elevation = FloatingActionButtonDefaults.elevation()
                        .elevation(interactionSource).value,

                    ) {
                    Box(modifier = Modifier

                        .clickable {
                            newGame()
                        }
                        .background(Color.Black)
                        .padding(10.dp), contentAlignment = Alignment.Center) {
                        Text(text = "New Game", color = Color.White, style = CTAButtonTextStyle)
                    }
                }



            }
        }
    }



}
