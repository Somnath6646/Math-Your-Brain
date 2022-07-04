package com.wenull.mathyourbrain.ui.theme
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.wenull.mathyourbrain.R

val AppFont =FontFamily(
        Font(R.font.circularstdbook, FontWeight.Normal),
        Font(R.font.circularstdmedium, FontWeight.Medium),
        Font(R.font.circularstdlight, FontWeight.Light),
        Font(R.font.circularstdbold, FontWeight.Bold)
)


val NewRatingOverlineTextStyle = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = Color(0xFF929292),
        letterSpacing = 0.25.sp
)
val NewRatingTextStyle = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp,
        color = Color(0xFF000000),
        letterSpacing = 0.25.sp
)
val ScoreOverlineTextStyle = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = Color(0xFF929292),
        letterSpacing = 0.25.sp
)
val ScoreTextStyle = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color(0xFF000000),
        letterSpacing = 0.25.sp
)

val CTAButtonTextStyle = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
)


val QuestionTextStyle = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = Color(0xFF000000),
        letterSpacing = 0.25.sp,
        lineHeight = 29.sp
)

val QuestionNumberTextStyle = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        color = Color(0xFFACACAC),
        letterSpacing =3.sp,
        lineHeight = 34.sp
)

val TimerTextStyle = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        color = Color(0xFFFFFFFF),
        letterSpacing = 1.sp,
        lineHeight = 34.sp
)

val OptionTextStyle = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = Color(0xFF7B7B7B),
        letterSpacing = 0.25.sp,
        lineHeight = 28.sp
)

val MemojiDialogTitle = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 19.02.sp,
        color = Color(0xFF000000),
        letterSpacing = 0.25.sp
)


val Typography = Typography(
        body1 = TextStyle(
                fontFamily = AppFont,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        )
        /* Other default text styles to override
        button = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp
        ),
        caption = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
        */
)