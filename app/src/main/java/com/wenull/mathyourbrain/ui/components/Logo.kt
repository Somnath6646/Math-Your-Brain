package com.wenull.mathyourbrain.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.wenull.mathyourbrain.R


@Composable
fun Logo(
    modifier: Modifier
){
    Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "logo", modifier = modifier)

}
