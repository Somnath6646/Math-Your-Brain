package com.wenull.mathyourbrain.ui.components.gamescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.wenull.mathyourbrain.ui.screens.main.TableContentTextStyle
import com.wenull.mathyourbrain.ui.theme.CTAButtonTextStyle
import com.wenull.mathyourbrain.ui.theme.MemojiDialogTitle

@Composable
fun ResignAlertDialog(yes: ()-> Unit, no: () -> Unit){
    Dialog(
        onDismissRequest = {  },
        properties = DialogProperties()
    ) {
        Column(
            Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .padding(25.dp)) {

            Text(
                "Do you want to resign?", style = MemojiDialogTitle, modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(bottom = 10.dp), textAlign = TextAlign.Center
            )

            Text(
                "Resigning this will make you lose ratings", style = TableContentTextStyle, modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(bottom = 20.dp), textAlign = TextAlign.Center
            )



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

                        .clickable { yes() }
                        .background(Color(0xFFF8F8F8))
                        .padding(10.dp), contentAlignment = Alignment.Center) {
                        Text(text = "Yes", color = Color.Black, style = CTAButtonTextStyle)
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
                            no()
                        }
                        .background(Color.Black)
                        .padding(10.dp), contentAlignment = Alignment.Center) {
                        Text(text = "No", color = Color.White, style = CTAButtonTextStyle)
                    }
                }



            }
        }
    }



}

