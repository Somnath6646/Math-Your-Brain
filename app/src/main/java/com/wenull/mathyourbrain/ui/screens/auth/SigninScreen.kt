package com.wenull.mathyourbrain.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.insets.systemBarsPadding
import com.wenull.mathyourbrain.HomeScreen
import com.wenull.mathyourbrain.R
import com.wenull.mathyourbrain.SignUp
import com.wenull.mathyourbrain.data.local.entities.User
import com.wenull.mathyourbrain.models.requests.SigninRequest
import com.wenull.mathyourbrain.models.response.SigninResponse
import com.wenull.mathyourbrain.ui.components.TextInput
import com.wenull.mathyourbrain.ui.screens.splash.CTAButton
import com.wenull.mathyourbrain.ui.theme.AppFont
import com.wenull.mathyourbrain.viewmodels.MainViewModel
import com.wenull.mathyourbrain.viewmodels.Task


val SignInTextStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Bold,
    fontSize = 33.02.sp,
    color = Color(0xFF000000),
    letterSpacing = 0.25.sp
)

val TextCTATextStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    letterSpacing = 0.sp
)

val WarningTextStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Normal,
    fontSize = 12.84.sp,
    letterSpacing = 0.25.sp,
    color = Color.Black
)

@Composable
fun Warning(warningText: String){


        Box(
            Modifier
                .clip(RoundedCornerShape(5.dp))
                .border(1.dp, Color(0xFFFF3A3A), RoundedCornerShape(5.dp))
                .background(Color(0xFFFFE5E5))
        )     {
            Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ic_warning)
                    , contentDescription = "ic_warning" )
                Text(warningText, style = WarningTextStyle, modifier = Modifier.padding(start = 10.dp))
            }
    }



}



@Composable
fun SigninScreen(navController: NavController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val (isVisible, setVisible) = remember { mutableStateOf(false)  }
    val (warningString, setWarningString) = remember { mutableStateOf("")  }

    var usernameState by remember { mutableStateOf(TextFieldValue()) }
    var passwordState by remember { mutableStateOf(TextFieldValue()) }

    var textFieldFocusState by remember { mutableStateOf(true) }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding(), verticalArrangement = Arrangement.SpaceBetween
    , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight(0.75f)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Sign in", style = SignInTextStyle, modifier = Modifier.padding(bottom = 32.dp))
                Column(Modifier.padding(horizontal = 24.dp, vertical = 12.dp), ) {
                    TextInput(

                        modifier = Modifier.padding(bottom = 12.dp)
                        ,
                        textFieldValue = usernameState,
                        onTextChanged = { usernameState = it },
                        // Only show the keyboard if there's no input selector and text field has focus
                        keyboardShown = textFieldFocusState,
                        keyboardActions = KeyboardActions(
                            onSend = {
                            }
                        )
                        ,

                        // Close extended selector if text field receives focus
                        onTextFieldFocused = { focused ->

                            textFieldFocusState = focused
                        },
                        focusState = textFieldFocusState,
                        borderRadius = 9.17.dp,
                        backgroundColor = Color(0xFFFBFBFB),
                        border = Color(0xFFF2F2F2),
                        borderWidth = 0.92.dp,
                        hint = "Username"
                    )

                    TextInput(

                        modifier = Modifier.padding(bottom = 12.dp)
                        ,
                        textFieldValue = passwordState,
                        onTextChanged = { passwordState = it },
                        // Only show the keyboard if there's no input selector and text field has focus
                        keyboardShown = textFieldFocusState,
                        keyboardActions = KeyboardActions(
                            onSend = {
                            }
                        )
                        ,

                        // Close extended selector if text field receives focus
                        onTextFieldFocused = { focused ->

                            textFieldFocusState = focused
                        },
                        focusState = textFieldFocusState,
                        borderRadius = 9.17.dp,
                        backgroundColor = Color(0xFFFBFBFB),
                        border = Color(0xFFF2F2F2),
                        borderWidth = 0.92.dp,
                        hint = "Password"
                    )


                }

            }

        }
        if(!warningString.isNullOrEmpty()){
            Warning(warningText = warningString)
        }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.padding(bottom = 20.dp)) {
                Text("Don’t have an account?", style = TextCTATextStyle, color = Color(0xFFC2C2C2) )
                Text(" Sign up", style = TextCTATextStyle, color = Color(0xFF000000) , modifier = Modifier.clickable {
                    navController.navigate(SignUp)
                })
            }
            CTAButton(onClick = {
                if( usernameState.text.isEmpty() && passwordState.text.isEmpty()){
                    setWarningString("Fill All Details")
                }else if (usernameState.text.isEmpty() || passwordState.text.isEmpty()){
                    if(usernameState.text.isEmpty()) setWarningString("Username is required")
                    if(passwordState.text.isEmpty()) setWarningString("Password is required")
                }else {

                viewModel.signin(
                    SigninRequest(username = usernameState.text, password = passwordState.text),

                    object : Task<SigninResponse?> {
                        override fun whileLoading() {
                            setVisible(true)
                        }

                        override fun onSucess(data: SigninResponse?) {
                            setVisible(false)
                            if (data != null) {
                                val user = data.user
                                viewModel.saveUserDetails(data.accessToken, User(avatar = user.avatar, games = user.games, id = user.id, name = user.name, rating = user.rating, username = user.username))
                                navController.navigate(HomeScreen){
                                    popUpTo(0){
                                        inclusive = true  }
                                }
                            }

                        }

                        override fun onError(error: String) {
                            setVisible(false)
                            setWarningString(error)

                        }

                    }



                ) }  }, isPrimary = true, text = "Sign in", modifier = Modifier.padding(bottom = 12.dp))


        }
    }

}

