package com.wenull.mathyourbrain

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.wenull.mathyourbrain.ui.components.LoadingDialog
import com.wenull.mathyourbrain.ui.screens.auth.SignupScreen
import com.wenull.mathyourbrain.ui.screens.auth.SigninScreen
import com.wenull.mathyourbrain.ui.screens.main.GameScreen
import com.wenull.mathyourbrain.ui.screens.main.HomeScreen
import com.wenull.mathyourbrain.ui.screens.main.LeaderBoard
import com.wenull.mathyourbrain.ui.screens.splash.SplashScreen
import com.wenull.mathyourbrain.ui.theme.MathYourBrainTheme
import com.wenull.mathyourbrain.ui.theme.SplashTheme
import com.wenull.mathyourbrain.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


const val Splash = "splash"
const val SignUp = "signup"
const val SignIn = "signin"
const val HomeScreen = "homescreen"
const val GameScreen = "gamescreen"
const val LeaderBoard = "leaderboard"

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalUnitApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()


    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.accessToken.observe(this, Observer {
           // Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        })
        viewModel.users.observe(this, Observer {
           // Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        })
        viewModel.toast.observe(this, Observer {
            it.getContentIfNotHandled().let {
                Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            }
        })

        if(BuildConfig.DEBUG){
            viewModel.debugToast.observe(this, Observer {
                it.getContentIfNotHandled().let {
                    Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
                }
            })
        }


        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
            }
            ProvideWindowInsets {
                val users  by  viewModel.users.observeAsState(null)
                if(users != null)
                {if( users!!.size >0) {
                    val user = users?.get(0);
                    if (user != null) {
                        viewModel.getUser(user)
                    }
                }
                }

                Main(systemUiController)
            }
        }
    }

    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    @RequiresApi(Build.VERSION_CODES.N)
    @ExperimentalUnitApi
    @Composable
    fun Main(systemUiController: SystemUiController) {
        val modifier = Modifier.systemBarsPadding()

        val entryAnim = slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
        val exitAnim =  slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700))


        Surface(color = MaterialTheme.colors.background, ) {
            val navController = rememberAnimatedNavController()

            AnimatedNavHost(navController = navController, startDestination = Splash) {

                composable(Splash, enterTransition = {entryAnim},
                    exitTransition = {exitAnim},
                    popEnterTransition = {entryAnim},
                    popExitTransition = {exitAnim}) {
                    SplashTheme {
                        val useDarkIcons = MaterialTheme.colors.isLight
                        SideEffect {
                            systemUiController.setSystemBarsColor(Color.Black, darkIcons = useDarkIcons)
                        }
                        SplashScreen(systemUiController,
                            navController = navController, viewModel =
                            viewModel
                        )
                    }
                }
                composable(
                    SignIn, enterTransition = {entryAnim},
                    exitTransition = {exitAnim},
                    popEnterTransition = {entryAnim},
                    popExitTransition = {exitAnim}) {
                    val isVisible = remember{ viewModel.isLoading }
                    if(isVisible.value){
                        LoadingDialog()
                    }
                    val useDarkIcons = MaterialTheme.colors.isLight
                    SideEffect {
                        systemUiController.isSystemBarsVisible = true
                        systemUiController.setSystemBarsColor(Color.White, darkIcons = useDarkIcons)
                    }
                    MathYourBrainTheme {
                        SideEffect {
                            systemUiController.setSystemBarsColor(Color.White, darkIcons = useDarkIcons)
                        }
                        SigninScreen(navController = navController, viewModel = viewModel)
                    }
                }

                composable(
                    SignUp, enterTransition = {entryAnim},
                    exitTransition = {exitAnim},
                    popEnterTransition = {entryAnim},
                    popExitTransition = {exitAnim}) {
                    val isVisible = remember{ viewModel.isLoading }
                    if(isVisible.value){
                        LoadingDialog()
                    }
                    val useDarkIcons = MaterialTheme.colors.isLight
                    SideEffect {
                        systemUiController.isSystemBarsVisible = true
                        systemUiController.setSystemBarsColor(Color.White, darkIcons = useDarkIcons)
                    }
                    MathYourBrainTheme{
                        SideEffect {
                            systemUiController.setSystemBarsColor(Color.White, darkIcons = useDarkIcons)
                        }
                        SignupScreen(navController = navController, viewModel = viewModel)
                    }
                }

                composable(
                    HomeScreen, enterTransition = {entryAnim},
                    exitTransition = {exitAnim},
                    popEnterTransition = {entryAnim},
                    popExitTransition = {exitAnim}) {
                    val useDarkIcons = MaterialTheme.colors.isLight
                    SideEffect {
                        systemUiController.isSystemBarsVisible = true
                        systemUiController.setSystemBarsColor(Color.White, darkIcons = useDarkIcons)
                    }
                    val isVisible = remember{ viewModel.isLoading }
                    if(isVisible.value){
                        LoadingDialog()
                    }
                    MathYourBrainTheme{
                        HomeScreen(navController, viewModel, modifier, systemUiController)
                    }
                }


                composable(
                    GameScreen, enterTransition = {entryAnim},
                    exitTransition = {exitAnim},
                    popEnterTransition = {entryAnim},
                    popExitTransition = {exitAnim}) {
                    val useDarkIcons = MaterialTheme.colors.isLight
                    SideEffect {
                        systemUiController.isSystemBarsVisible = true
                        systemUiController.setSystemBarsColor(Color.White, darkIcons = useDarkIcons)
                    }
                    val isVisible = remember{ viewModel.isLoading }
                    if(isVisible.value){
                        LoadingDialog()
                    }
                    MathYourBrainTheme{
                        GameScreen(navController, viewModel, modifier, systemUiController)
                    }
                }
                composable(
                    LeaderBoard, enterTransition = {entryAnim},
                    exitTransition = {exitAnim},
                    popEnterTransition = {entryAnim},
                    popExitTransition = {exitAnim}) {
                    val useDarkIcons = MaterialTheme.colors.isLight
                    SideEffect {
                        systemUiController.isSystemBarsVisible = true
                        systemUiController.setSystemBarsColor(Color.White, darkIcons = useDarkIcons)
                    }
                    val isVisible = remember{ viewModel.isLoading }
                    if(isVisible.value){
                        LoadingDialog()
                    }
                    MathYourBrainTheme{
                        LeaderBoard(navController, viewModel, modifier, systemUiController)
                    }
                }


            }
        }
    }

}


