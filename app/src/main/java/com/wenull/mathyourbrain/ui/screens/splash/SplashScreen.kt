package com.wenull.mathyourbrain.ui.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.qrate.android.*
import com.wenull.mathyourbrain.HomeScreen
import com.wenull.mathyourbrain.R
import com.wenull.mathyourbrain.SignUp
import com.wenull.mathyourbrain.Splash
import com.wenull.mathyourbrain.ui.theme.AppFont
import com.wenull.mathyourbrain.ui.theme.CTAButtonTextStyle
import com.wenull.mathyourbrain.viewmodels.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(systemUiController: SystemUiController, navController: NavController, viewModel: MainViewModel = viewModel()) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    val useDarkIcons = MaterialTheme.colors.isLight
    val users by  viewModel.users.observeAsState(null)
    SideEffect {
        systemUiController.setSystemBarsColor(Color.White, darkIcons = useDarkIcons)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }))
        delay(500L)


        if(viewModel.accessToken.value == null) {
            navController.navigate(SignUp) {
                popUpTo(Splash) { inclusive = true }
            }
        }else{
            navController.navigate(HomeScreen) {
                popUpTo(Splash) { inclusive = true }
            }
        }
    }

    // Image
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
        Image(painterResource(id = R.drawable.ic_logo), contentDescription = "", modifier = Modifier.requiredWidth(150.dp).aspectRatio(1f/1f))
    }
}

val SplashAppNameTextStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Bold,
    fontSize = 55.37.sp,
    color = Color.White,
    lineHeight = 24.sp
)


@Composable
fun CTAButton(onClick: () -> Unit, isPrimary: Boolean, text: String, modifier : Modifier = Modifier, elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
){
    var bgColor = Color(0xFFFAF8F8)
    var textColor = Color.Black
    if(isPrimary){
        textColor = Color(0xFFFFFFFF)
        bgColor = Color.Black
    }
    val interactionSource = remember { MutableInteractionSource() }
   Surface(modifier = modifier
       .fillMaxWidth(0.9f)
       .clip(RoundedCornerShape(15.dp)),
       elevation = elevation.elevation(interactionSource).value,

       ) {
       Box(modifier = Modifier

           .clickable { onClick() }
           .background(bgColor)
           .padding(20.dp)
           , contentAlignment = Alignment.Center){
           Text(text = text, color = textColor, style = CTAButtonTextStyle)
       }
    }


}