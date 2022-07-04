package com.wenull.mathyourbrain.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wenull.mathyourbrain.ui.theme.AppFont


val hintTextStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Normal,
    fontSize = 14.68.sp,
    color = Color(0xFF828282)
)

@Composable
fun TextInput(
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (TextFieldValue) -> Unit,
    textFieldValue: TextFieldValue,
    keyboardShown: Boolean,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = keyboardType,
        imeAction = ImeAction.Send
    ),
    keyboardActions: KeyboardActions = KeyboardActions {

    },
    borderWidth: Dp,
    backgroundColor: Color,
    border: Color,
    borderRadius: Dp,
    hint: String,
    onTextFieldFocused: (Boolean) -> Unit,
    focusState: Boolean,
    padding: PaddingValues = PaddingValues(17.dp),
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current.copy(color = LocalContentColor.current),
    hintStyle: TextStyle = hintTextStyle
) {
    val descr = "descr"
    var (lastFocusState) = remember { mutableStateOf(true) }
    Row(
        modifier = modifier.semantics {
            contentDescription = descr
        }.onFocusChanged { state ->
            if (lastFocusState != state.isFocused) {
                onTextFieldFocused(state.isFocused)
            }
            lastFocusState = state.isFocused
        }


    ) {
        Box(Modifier.clip(RoundedCornerShape(borderRadius))
            .border(borderWidth, border, RoundedCornerShape(borderRadius))
            .background(backgroundColor)) {

            Box(
                modifier = Modifier
                    .padding(paddingValues = padding)
                    .fillMaxWidth(1f)
                ,
                contentAlignment = Alignment.Center
            ) {


                BasicTextField(
                    value = textFieldValue,
                    onValueChange = { onTextChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                        .align(Alignment.Center)
                        ,
                    keyboardOptions = keyboardOptions,
                    maxLines = 1,
                    keyboardActions = keyboardActions,
                    textStyle = textStyle,
                )

                val disableContentColor =
                    MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
                if (textFieldValue.text.isEmpty() && !focusState) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 10.dp),
                        text = AnnotatedString(hint),
                        style = hintStyle
                    )


                }
            }
        }
    }
}
