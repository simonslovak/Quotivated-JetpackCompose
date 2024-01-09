package com.utb.quotivated.custom

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextWithShadow(text: String, fontSize: Int) {
    Text(
        text = text,
        color = Color.Black,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        modifier = Modifier
            .wrapContentSize()
            .offset((-0.5).dp, (-0.5).dp)
            .blur(1.5.dp)
    )
    Text(
        text = text,
        color = Color.Black,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        modifier = Modifier
            .wrapContentSize()
            .offset(0.5.dp, 0.5.dp)
            .blur(1.5.dp)
    )
    Text(
        text = text,
        color = Color.Cyan,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        modifier = Modifier
            .wrapContentSize()
    )
}