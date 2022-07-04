package com.wenull.mathyourbrain.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wenull.mathyourbrain.R
import com.wenull.mathyourbrain.data.local.entities.User
import com.wenull.mathyourbrain.ui.screens.main.ratingTextStyle
import com.wenull.mathyourbrain.ui.screens.main.usernameTextStyle

@Composable
fun Header(user: User, modifier: Modifier = Modifier, onclick: () -> Unit) {

        Row(modifier) {
            Box(
                Modifier
                    .padding(top = 30.dp, bottom = 20.dp)

                    .clip(RoundedCornerShape(100))
            ) {
                Box(Modifier.background(Color(246,246,246))) {
                    Row(Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
                        AsyncImage(
                            model = user.avatar,
                            modifier = Modifier.size(45.dp),
                            contentDescription = null
                        )
                        Column(
                            Modifier
                                .padding(end = 9.dp)
                                .align(Alignment.CenterVertically)) {
                            Text(
                                text = user.name,
                                style = usernameTextStyle,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = null,
                                    modifier = Modifier.size(12.dp)
                                )
                                Text(text = "${user.rating}", style = ratingTextStyle, modifier = Modifier.padding(start= 2.dp))
                            }
                        }
                        Column(
                            Modifier
                                .padding(horizontal = 9.dp)
                                .align(Alignment.CenterVertically)) {

                            Icon(
                                painter = painterResource(id = R.drawable.down),
                                contentDescription = null,
                                tint = Color(145, 145, 145)
                            )
                        }
                    }
                }
            }
        }

}