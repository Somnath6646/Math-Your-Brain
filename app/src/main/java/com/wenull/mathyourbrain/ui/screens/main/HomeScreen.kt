package com.wenull.mathyourbrain.ui.screens.main

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.SystemUiController
import com.wenull.mathyourbrain.viewmodels.MainViewModel
import com.wenull.mathyourbrain.R
import com.wenull.mathyourbrain.data.local.entities.GameX
import com.wenull.mathyourbrain.models.basic.Game
import com.wenull.mathyourbrain.ui.components.Header
import com.wenull.mathyourbrain.ui.screens.splash.CTAButton
import com.wenull.mathyourbrain.ui.theme.AppFont


val usernameTextStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    color = Color(0xFF000000),
    letterSpacing = 0.sp
)

val ratingTextStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    color = Color(0xFF000000),
    letterSpacing = 0.sp
)

val tabTextStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Medium,
    fontSize = 13.75.sp,
    color = Color(0xFF000000),
    letterSpacing = 0.sp
)


@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel, modifier: Modifier, systemUiController: SystemUiController) {



    val useDarkIcons = MaterialTheme.colors.isLight
    val users by  viewModel.users.observeAsState(null)
    SideEffect {
        systemUiController.setSystemBarsColor(Color.White, darkIcons = useDarkIcons)
    }
    if(users != null)
    {if( users!!.size >0) {
    val user = users?.get(0);
    if(user!=null ) {



        Box(
            Modifier
                .systemBarsPadding(true)
                .fillMaxSize(1f),
        ) {

            Column(Modifier.verticalScroll(rememberScrollState(), true)) {
                Header(user = user, modifier = Modifier.align(Alignment.CenterHorizontally)) {

                }

                Row(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(1f)
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    NewGameCard {
                        viewModel.createGame(user)
                        navController.navigate(com.wenull.mathyourbrain.GameScreen)
                    }

                    LeadershipCard {
                        viewModel.getLeaderboard(user)
                        navController.navigate(com.wenull.mathyourbrain.LeaderBoard)
                    }

                    LessonsCard {

                    }


                }

                Box(Modifier.padding(start = 15.dp, top = 15.dp, bottom = 100.dp, end = 15.dp)) {
                    GamesPlayedTable(games = user.games)
                }
            }

                CTAButton(onClick = {
                    viewModel.createGame(user)
                    navController.navigate(com.wenull.mathyourbrain.GameScreen)
                }, isPrimary = true, text = "New Game", modifier = Modifier
                    .align(
                        BottomCenter
                    )
                    .padding(bottom = 15.dp))


        }

        




        }
    }
    }
}


@Composable
fun NewGameCard(onClick: () -> Unit){
    Box(
        Modifier
            .padding(horizontal = 5.dp, vertical = 12.dp)
            .clip(
                RoundedCornerShape(13.dp)
            )
            .clickable {
                onClick()
            }
            .background(Color(248, 248, 248))
    ) {
        Column(
            Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.plus__),
                contentDescription = null,
                modifier = Modifier
                    .height(90.dp)
                    .aspectRatio(1f / 1f)
                    .padding(13.dp)
            )

            Text(
                text = "New Game",
                style = tabTextStyle,
                modifier = Modifier
                    .padding(bottom = 15.dp, start = 13.dp, end = 13.dp),
                textAlign = TextAlign.Center
            )


        }
    }
}

@Composable
fun LeadershipCard(onClick: () -> Unit){
    Box(
        Modifier
            .padding(horizontal = 5.dp, vertical = 12.dp)
            .clip(
                RoundedCornerShape(13.dp)
            )
            .clickable { onClick() }
            .background(Color(248, 248, 248))) {
        Column( Modifier
            .padding(horizontal = 5.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.pi),
                contentDescription = null,
                modifier = Modifier
                    .height(90.dp)
                    .aspectRatio(1f / 1f)
                    .padding(13.dp)
            )

            Text(
                text = "Leaderboard",
                style = tabTextStyle,
                modifier = Modifier
                    .padding(bottom = 15.dp, start = 13.dp, end = 13.dp),
                textAlign = TextAlign.Center
            )


        }
    }
}

@Composable
fun LessonsCard(onClick: () -> Unit){
    Box(
        Modifier
            .padding(horizontal = 5.dp, vertical = 12.dp)

            .clip(
                RoundedCornerShape(13.dp)
            )
            .clickable {
                onClick()
            }
            .background(Color(248, 248, 248))) {
        Column( Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.google_classroom),
                contentDescription = null,
                modifier = Modifier
                    .height(90.dp)
                    .aspectRatio(1f / 1f)
                    .padding(13.dp)
            )

            Text(
                text = "Lessons",
                style = tabTextStyle,
                modifier = Modifier
                    .padding(bottom = 15.dp, start = 13.dp, end = 13.dp),
                textAlign = TextAlign.Center
            )


        }
    }
}



val OverlineTextStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    color = Color(109,109,109),
    letterSpacing = 2.sp
)

val TableHeaderTextStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    color = Color(164,164,164),
    letterSpacing = 0.25.sp
)
val TableContentTextStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Normal,
    fontSize = 15.25.sp,
    color = Color(95, 95, 95, 255),
    letterSpacing = 0.95.sp
)



@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GamesPlayedTable(games: List<GameX>){

   var games =   games.reversed()

    Column() {
        Text(text = "RECENT GAMES", style = OverlineTextStyle, modifier = Modifier.padding(horizontal = 10.5.dp, vertical = 20.dp))

        Box(
            Modifier
                .clip(RoundedCornerShape(16.dp))

                .border(2.86.dp, Color(248, 248, 248, 255), RoundedCornerShape(16.dp))
                .fillMaxWidth(1f)

                ){
            Row(
                Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
                    .fillMaxWidth(1f), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(){
                        Text("Date", style = TableHeaderTextStyle, modifier = Modifier.padding(8.5.dp))

                    games.forEach(){ game->
                        Text(text = "${convertToCustomFormat(game.createdAt)}", style = TableContentTextStyle, modifier = Modifier.padding(8.5.dp))
                    }
                }

                Column(){
                        Text("Time", style = TableHeaderTextStyle, modifier = Modifier.padding(8.5.dp))

                    games.forEach(){ game->
                        Text(text = "${game.completedTime/60}m ${game.completedTime%60}s", style = TableContentTextStyle,  modifier = Modifier.padding(8.5.dp))
                    }
                }

                Column(){
                        Text("Score", style = TableHeaderTextStyle, modifier = Modifier.padding(8.5.dp))

                    games.forEach(){ game->
                        Text(text = "${game.scoredPoints}", style = TableContentTextStyle, modifier = Modifier.padding(8.5.dp))
                    }
                }
                Column(){
                        Text("Rating", style = TableHeaderTextStyle, modifier = Modifier.padding(8.5.dp))

                games.forEach(){ game->
                        Text(text = "${game.rating}", style = TableContentTextStyle, modifier = Modifier.padding(8.5.dp))
                    }
                }
            }

        }
    }

    

}

@SuppressLint("SimpleDateFormat")
private fun convertToCustomFormat(dateStr: String?): String {
    val utc = TimeZone.getTimeZone("UTC")
    val sourceFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    val destFormat = SimpleDateFormat("dd/MM/YYYY")
    sourceFormat.timeZone = utc
    val convertedDate = sourceFormat.parse(dateStr)
    return destFormat.format(convertedDate)
}

