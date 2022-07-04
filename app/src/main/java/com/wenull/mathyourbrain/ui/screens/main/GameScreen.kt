package com.wenull.mathyourbrain.ui.screens.main

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.SystemUiController
import com.wenull.mathyourbrain.data.local.entities.User
import com.wenull.mathyourbrain.models.response.SigninResponse
import com.wenull.mathyourbrain.models.response.UpdateGameResponse
import com.wenull.mathyourbrain.ui.components.Timer
import com.wenull.mathyourbrain.ui.components.gamescreen.GameCompleted
import com.wenull.mathyourbrain.ui.components.gamescreen.Option
import com.wenull.mathyourbrain.ui.components.gamescreen.ResignAlertDialog
import com.wenull.mathyourbrain.ui.screens.splash.CTAButton
import com.wenull.mathyourbrain.ui.theme.AppFont
import com.wenull.mathyourbrain.ui.theme.CTAButtonTextStyle
import com.wenull.mathyourbrain.ui.theme.QuestionNumberTextStyle
import com.wenull.mathyourbrain.ui.theme.QuestionTextStyle
import com.wenull.mathyourbrain.viewmodels.MainViewModel
import com.wenull.mathyourbrain.viewmodels.Task
import kotlinx.coroutines.delay








var points: Int = 0
val answers = mutableListOf<Int>()
val correctQuestions = mutableListOf<String>()

val tag = "654987"

@OptIn(ExperimentalUnitApi::class)
@Composable
fun GameScreen(navController: NavController, viewModel: MainViewModel, modifier: Modifier, systemUiController: SystemUiController) {



    val useDarkIcons = MaterialTheme.colors.isLight
    val users by  viewModel.users.observeAsState(null)
    val value by viewModel.value
    var isSubmitButtonAvailable by remember{ mutableStateOf(false) }

    SideEffect {
        systemUiController.setSystemBarsColor(Color.White, darkIcons = useDarkIcons)
    }




    val createGameResponse by viewModel.createGameResponse
    val user = users?.get(0)

    if(user!=null) {
        if (createGameResponse != null){
        if (createGameResponse!!.questions.size> 0) {
            var currentQuestionNumber by remember { mutableStateOf<Int>(1) }

            var resignDialogVisible by remember { mutableStateOf(false) }
            var gameCompletedDialogVisible by remember { mutableStateOf(false) }
            var newRating by remember{ mutableStateOf(user.rating)}
            var correctQuestionsForDialog by remember{ mutableStateOf(0)}
            var completedTimeForDialog by remember{ mutableStateOf(0L)}
            var scoreForDialog by remember{ mutableStateOf(0)}
            val currentTime by viewModel.currentTime.observeAsState()

            Column(
                Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .verticalScroll(rememberScrollState(), true), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BackHandler() {
                    resignDialogVisible = true
                }


                if (gameCompletedDialogVisible) {

                    GameCompleted(newGame = {
                        correctQuestionsForDialog = 0
                        gameCompletedDialogVisible = false
                        viewModel.createGame(user)
                        navController.popBackStack()
                        navController.navigate(com.wenull.mathyourbrain.GameScreen)
                    }, leave = {
                        correctQuestionsForDialog = 0
                        gameCompletedDialogVisible = false
                        navController.popBackStack()

                        }, newRating = newRating, correctQuestions =  correctQuestionsForDialog, completedTime =  completedTimeForDialog, totalScore = 40, scoredPoints = scoreForDialog)
                }


                if (resignDialogVisible) {
                    ResignAlertDialog(yes = {

                            viewModel.updateGame(
                                userId = user.id,
                                gameId = createGameResponse!!.id,
                                correctQuestions,
                                completedTime = (((20*10000 - viewModel.currentTime.value?.peekContent()!!)/ 1000.0)),
                                points,
                                resigned = true,
                                rating = getRatingByPoints(points, user.rating),
                                object : Task<UpdateGameResponse> {
                                    override fun whileLoading() {
                                        gameCompletedDialogVisible = false
                                    }

                                    override fun onSucess(data: UpdateGameResponse) {

                                        gameCompletedDialogVisible = true
                                        scoreForDialog = points

                                        completedTimeForDialog = data.completedTime.toLong()
                                        println("798040 completed tim onsucess: ${completedTimeForDialog}")
                                        correctQuestionsForDialog = correctQuestions.size
                                        newRating = data.rating
                                        points = 0
                                        answers.clear()
                                        correctQuestions.clear()
                                    }

                                    override fun onError(error: String) {

                                        gameCompletedDialogVisible = false
                                    }
                                })
                        viewModel.stopTimer()

                        resignDialogVisible = false
                    }) {
                        resignDialogVisible = false
                    }
                }

                currentTime?.getContentIfNotHandled()?.let {
                    Timer(
                        totalTime = 1000L * 200,
                        modifier = Modifier.padding(top = 10.dp),
                        value = value,
                        currentTime = it,
                    ) {
                        Log.i(tag, "time finished!")
                        viewModel.updateGame(
                            userId = user.id,
                            gameId = createGameResponse!!.id,
                            correctQuestions,
                            completedTime = (((20 * 10000 - viewModel.currentTime.value?.peekContent()!!) / 1000.0)),
                            points,
                            resigned = true,
                            rating = getRatingByPoints(points, user.rating),
                            object : Task<UpdateGameResponse> {
                                override fun whileLoading() {

                                }

                                override fun onSucess(data: UpdateGameResponse) {
                                    gameCompletedDialogVisible = true
                                    completedTimeForDialog = data.completedTime.toLong()
                                    scoreForDialog = data.scoredPoints
                                    correctQuestionsForDialog = data.correctQuestions.size
                                    newRating = data.rating
                                    points = 0
                                    answers.clear()
                                    correctQuestions.clear()

                                }

                                override fun onError(error: String) {


                                }
                            })
                        viewModel.stopTimer()
                    }
                }



                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(horizontal = 15.dp)
                ) {

                    Text(
                        "$currentQuestionNumber/20  ${createGameResponse!!.questions[currentQuestionNumber - 1].correctOption}",
                        style = QuestionNumberTextStyle,
                        modifier = Modifier.padding(top = 65.dp, bottom = 15.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        createGameResponse!!.questions[currentQuestionNumber - 1].question,
                        style = QuestionTextStyle,
                        modifier = Modifier.padding(
                            top = 10.dp,
                            bottom = 35.dp,
                            start = 15.dp,
                            end = 15.dp
                        ),
                        textAlign = TextAlign.Center
                    )

                    createGameResponse!!.questions[currentQuestionNumber - 1].options.forEachIndexed { index, option ->
                        Option(index = index, text = option) { index ->
                            answers.add(index)
                            if (createGameResponse!!.questions[currentQuestionNumber - 1].correctOption == index) {
                                points += 2
                                correctQuestions.add(createGameResponse!!.questions[currentQuestionNumber - 1].id)
                            }
                            if (currentQuestionNumber < 20) {
                                currentQuestionNumber = (currentQuestionNumber + 1)
                            } else {
                                isSubmitButtonAvailable = true
                            }

                        }
                    }
                }

                if (isSubmitButtonAvailable)
                    CTAButton(onClick = {

                        viewModel.updateGame(
                            userId = user.id,
                            gameId = createGameResponse!!.id,
                            correctQuestions,
                            completedTime = (((20*10000 -viewModel.currentTime.value?.peekContent()!! )/ 1000.0)),
                            points,
                            resigned = false,
                            rating = getRatingByPoints(points, user.rating),
                            object : Task<UpdateGameResponse> {
                                override fun whileLoading() {

                                }

                                override fun onSucess(data: UpdateGameResponse) {

                                    gameCompletedDialogVisible = true
                                    completedTimeForDialog = data.completedTime.toLong()
                                    scoreForDialog = points
                                    correctQuestionsForDialog = correctQuestions.size
                                    newRating = data.rating
                                    points = 0
                                    answers.clear()
                                    correctQuestions.clear()

                                }

                                override fun onError(error: String) {

                                    Log.i("124478", error)

                                }
                            })
                        viewModel.stopTimer()
                    }, isPrimary = true, text = "Submit")
            }

        }
        }}
}


fun getRatingByPoints(point: Int, initalRating: Int): Int{
    val a: Double = (((point - 20.0)/40.0)*10.0)
    val gif = Math.ceil(a)
    val delta_r = gif*2
    return initalRating+delta_r.toInt()
}



