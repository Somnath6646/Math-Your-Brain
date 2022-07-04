package com.wenull.mathyourbrain.ui.components.gamescreen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wenull.mathyourbrain.ui.theme.OptionTextStyle

@Composable
fun Option(index: Int, text: String, onClick: (Int) -> Unit){
    Box(
        Modifier

            .padding(start = 12.dp, end = 12.dp, top = 5.dp, bottom = 15.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color(244, 244, 244), RoundedCornerShape(20.dp))
            .clickable {
                onClick(index)
            }
            .fillMaxWidth()) {
        Row(Modifier.padding(top = 15.dp, bottom = 15.dp, start =15.dp, end = 15.dp)) {
            Text(text = "${(65+index).toChar()}", style = OptionTextStyle, modifier = Modifier.padding(start = 15.dp, end = 24.dp, top = 15.dp, bottom = 15.dp))
            Text(text = "${text}", style = OptionTextStyle, modifier = Modifier.padding(start = 15.dp, end = 24.dp, top = 15.dp, bottom = 15.dp))
        }
    }
}
