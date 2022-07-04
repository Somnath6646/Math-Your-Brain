package com.wenull.mathyourbrain.ui.screens.main

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.SystemUiController
import com.wenull.mathyourbrain.ui.theme.AppFont
import com.wenull.mathyourbrain.viewmodels.MainViewModel


val LeaderboardTitle = TextStyle(
fontFamily = AppFont,
fontWeight = FontWeight.Bold,
fontSize = 33.02.sp,
color = Color(0xFF000000),
letterSpacing = 0.25.sp
)


@Composable
fun LeaderBoard(navController: NavController, viewModel: MainViewModel, modifier: Modifier, systemUiController: SystemUiController) {



    val useDarkIcons = MaterialTheme.colors.isLight
    val users by  viewModel.users.observeAsState(null)
    SideEffect {
        systemUiController.setSystemBarsColor(Color.White, darkIcons = useDarkIcons)
    }

    val getLeaderboardResponse by remember { viewModel.leaderboardResponse }

    if(getLeaderboardResponse!= null){
        val list = getLeaderboardResponse

        Column(
            Modifier
                .systemBarsPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState(), true)){
            Text("Leaderboard", style = LeaderboardTitle, modifier = Modifier.padding(horizontal = 20.dp, vertical = 30.dp))

            Box(
                Modifier
                    .clip(RoundedCornerShape(16.dp))

                    .fillMaxWidth(1f)

            ){
                Column(Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
                    .fillMaxSize(1f),) {
                    Row(Modifier
                        .fillMaxWidth(1f),){
                        Text("Rank", style = TableHeaderTextStyle, modifier = Modifier.padding(8.5.dp).fillMaxWidth(1/6f), textAlign = TextAlign.Start)
                        Text("Player", style = TableHeaderTextStyle, modifier = Modifier.padding(8.5.dp ).fillMaxWidth(1/1.4f), textAlign = TextAlign.Start)
                        Text("Rating", style = TableHeaderTextStyle, modifier = Modifier.padding(8.5.dp), textAlign = TextAlign.Start)
                    }
                    list?.forEachIndexed() {index, player ->
                        Row(
                            Modifier.fillMaxWidth(1f), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "#$index", style = TableContentTextStyle, modifier = Modifier.padding(8.5.dp), textAlign = TextAlign.Start)
                            Row( modifier = Modifier.padding(8.5.dp).fillMaxWidth(1/1.3f), verticalAlignment = Alignment.CenterVertically) {
                                AsyncImage(
                                    model = player.avatar,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(end = 5.dp),
                                    contentDescription = null
                                )
                                Text(text = "${player.username}", style = TableContentTextStyle, color = Color.Black,modifier = Modifier.fillMaxSize())
                            }
                            Text(text = "${player.rating}", style = TableContentTextStyle, modifier = Modifier.padding(8.5.dp), textAlign = TextAlign.Start)

                        }
                    }

                }
            }

        }
    }

}