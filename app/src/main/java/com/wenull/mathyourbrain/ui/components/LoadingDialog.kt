package com.wenull.mathyourbrain.ui.components

import androidx.compose.foundation.background
import com.wenull.mathyourbrain.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.wenull.mathyourbrain.ui.components.AlertDialog
import com.wenull.mathyourbrain.ui.theme.AppFont
import com.wenull.mathyourbrain.ui.theme.MemojiDialogTitle

@Composable
fun LoadingDialog(){
    Dialog(onDismissRequest = {  }) {
        Box(
            Modifier
                .height(80.dp).width(80.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Black), contentAlignment = Alignment.Center) {
            Loader()
        }

    }

}



@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    LottieAnimation(composition,
        Modifier
            .fillMaxSize()
            .padding(0.dp), contentScale = ContentScale.Crop)
}




@Composable
fun MemojiSelector(links: List<String>, onClick:(String) -> Unit){

    Dialog(onDismissRequest = {  }) {
    Box(
        modifier =  Modifier.clip(RoundedCornerShape(20.dp))){
            var count = 0

            if(links.isEmpty()) {

            }else {
            Column(){


                Text("Choose your avatar", style = MemojiDialogTitle, modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(bottom = 25.dp), textAlign = TextAlign.Center)

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