package com.wenull.mathyourbrain.ui.screens.auth

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.systemBarsPadding
import com.wenull.mathyourbrain.ui.components.LoadingDialog
import com.wenull.mathyourbrain.ui.components.MemojiSelector
import com.wenull.mathyourbrain.HomeScreen
import com.wenull.mathyourbrain.SignIn
import com.wenull.mathyourbrain.data.local.entities.User
import com.wenull.mathyourbrain.models.requests.SignupRequest
import com.wenull.mathyourbrain.models.response.GetAllAvatarsResponse
import com.wenull.mathyourbrain.models.response.SigninResponse
import com.wenull.mathyourbrain.models.response.SignupResponse
import com.wenull.mathyourbrain.ui.components.TextInput
import com.wenull.mathyourbrain.ui.components.hintTextStyle
import com.wenull.mathyourbrain.ui.screens.auth.SignInTextStyle
import com.wenull.mathyourbrain.ui.screens.auth.TextCTATextStyle
import com.wenull.mathyourbrain.ui.screens.auth.Warning
import com.wenull.mathyourbrain.ui.screens.splash.CTAButton
import com.wenull.mathyourbrain.viewmodels.MainViewModel
import com.wenull.mathyourbrain.viewmodels.Task

@Composable
fun SignupScreen(
    viewModel: MainViewModel = viewModel(),
    navController: NavController) {
    val (isVisible, setVisible) = remember { mutableStateOf(false)  }
    val (warningString, setWarningString) = remember { mutableStateOf("")  }

    var nameState  by remember { mutableStateOf(TextFieldValue()) }
    var usernameState by remember { mutableStateOf(TextFieldValue()) }
    var passwordState by remember { mutableStateOf(TextFieldValue()) }
    var avatarState by remember { mutableStateOf<String?>(null) }

    var textFieldFocusState by remember { mutableStateOf(true) }
    var isChoosingMemoji by remember{mutableStateOf(false)}
    var anAvatarSelected by remember{mutableStateOf(false)}

    var avatars by remember { mutableStateOf<List<String>>(listOf())}

    if(isChoosingMemoji && avatars.size>0)
    MemojiSelector(avatars) { string ->
        avatarState = (string)
        isChoosingMemoji = false
        anAvatarSelected = true
    }


    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding(), verticalArrangement = Arrangement.SpaceBetween
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight(0.75f)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Sign up", style = SignInTextStyle, modifier = Modifier.padding(bottom = 32.dp))

                Column(Modifier.padding(horizontal = 24.dp, vertical = 12.dp), ) {


                    TextInput(

                        modifier = Modifier.padding(bottom = 12.dp)
                        ,
                        textFieldValue = nameState,
                        onTextChanged = { nameState = it },
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
                        hint = "Name"
                    )


                    TextInput(
                        modifier = Modifier.padding(bottom = 12.dp)
                        ,
                        textFieldValue = usernameState,
                        onTextChanged = { usernameState = it },
                        // Only show the keyboard if there's no input selector and text field has focus
                        keyboardShown = textFieldFocusState,
                        keyboardActions = KeyboardActions(
                            onSend = {
                            })
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


                    Row(
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .clickable {
                                isChoosingMemoji = !isChoosingMemoji
                                viewModel.getAllAvatars(object : Task<GetAllAvatarsResponse> {
                                    override fun whileLoading() {
                                    }

                                    override fun onSucess(data: GetAllAvatarsResponse) {
                                        avatars = data.avatars
                                        Log.i("12245", "Avatars $avatars")
                                    }

                                    override fun onError(error: String) {
                                    }

                                })
                            }
                    ) {
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(9.17.dp))
                                .border(
                                    0.92.dp, Color(0xFFF2F2F2),
                                    RoundedCornerShape(9.17.dp)
                                )
                                .background(Color(0xFFFBFBFB))
                        ) {

                            Box(
                                modifier = Modifier
                                    .padding(paddingValues = PaddingValues(17.dp))
                                    .fillMaxWidth(1f),
                                contentAlignment = Alignment.Center
                            ) {


                                Text(
                                    text = if(anAvatarSelected) "Chose An Avatar" else "Choose An Avatar",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp)
                                        .align(Alignment.Center),
                                    style = if(!anAvatarSelected) hintTextStyle else LocalTextStyle.current.copy(color = LocalContentColor.current)
                                )
                            }
                        }
                    }



                }

            }

        }
        if(!warningString.isNullOrEmpty()){
            Warning(warningText = warningString)
        }



        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.padding(bottom = 20.dp)) {
                Text("Already have an account?", style = TextCTATextStyle, color = Color(0xFFC2C2C2) )
                Text(" Sign in", style = TextCTATextStyle, color = Color(0xFF000000) , modifier = Modifier.clickable {
                    navController.navigate(SignIn)
                })
            }
            CTAButton(onClick = {
                if(avatarState.isNullOrEmpty()&&nameState.text.isEmpty()&& usernameState.text.isEmpty() && passwordState.text.isEmpty()){
                    setWarningString("Fill All Details")
                }else if (avatarState.isNullOrEmpty() || nameState.text.isEmpty() || usernameState.text.isEmpty() || passwordState.text.isEmpty()){
                    if(nameState.text.isEmpty()) setWarningString("Name is required")
                    if(usernameState.text.isEmpty()) setWarningString("Username is required")
                    if(passwordState.text.isEmpty()) setWarningString("Password is required")
                    if(avatarState.isNullOrEmpty()) setWarningString("Password is required")
                }else {
                viewModel.signup(



                        SignupRequest(
                            name = nameState.text,
                            username = usernameState.text,
                            password = passwordState.text,
                            avatar = avatarState!!
                        ),

                        object : Task<SignupResponse?> {
                            override fun whileLoading() {
                                setVisible(true)
                            }

                            override fun onSucess(data: SignupResponse?) {
                                setVisible(false)
                                if (data != null) {
                                    val user = data.user
                                    Log.i("12245", "viewmodel save calling $user")
                                    viewModel.saveUserDetails(
                                        data.accessToken,
                                        User(
                                            avatar = user.avatar,
                                            games = user.games,
                                            id = user.id,
                                            name = user.name,
                                            rating = user.rating,
                                            username = user.username
                                        )
                                    )
                                    navController.navigate(HomeScreen) {
                                        popUpTo(0) {
                                            inclusive = true
                                        }
                                    }
                                }

                            }

                            override fun onError(error: String) {
                                setVisible(false)
                                setWarningString(error)

                            }


                    }


                )
            }}, isPrimary = true, text = "Sign up", modifier = Modifier.padding(bottom = 12.dp))


        }

    }
}


