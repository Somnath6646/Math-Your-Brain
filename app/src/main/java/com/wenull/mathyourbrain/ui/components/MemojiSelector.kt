package com.wenull.mathyourbrain.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.wenull.mathyourbrain.ui.theme.MemojiDialogTitle


@Composable
fun MemojiSelector(links: List<String>, dismiss: () -> Unit, onClick:(String) -> Unit){

    Dialog(onDismissRequest = {dismiss()}) {
        Box(
            modifier =  Modifier.clip(RoundedCornerShape(20.dp)).background(Color.White)){
            var count = 0

            if(links.isEmpty()) {

            }else {
                Column(){


                    Text("Choose your avatar", style = MemojiDialogTitle, modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(25.dp), textAlign = TextAlign.Center)

                    Row(horizontalArrangement = Arrangement.SpaceEvenly){
                        for (i in 0..3) {
                            Column(){
                                for (j in 0..3) {
                                    if(count<links.size) {
                                        val link = links[count]
                                        SubcomposeAsyncImage(
                                            model = link,
                                            contentDescription = "null",
                                            modifier = Modifier
                                                .size(100.dp)
                                                .clickable {
                                                    onClick(link)
                                                }
                                        ){
                                            val state = painter.state
                                            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {

                                            } else {
                                                SubcomposeAsyncImageContent()
                                            }
                                        }



                                        count ++;
                                    }
                                }
                            }


                        }
                    }




                }
            }
        }
    }

}