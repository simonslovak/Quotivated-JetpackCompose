package com.utb.quotivated.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomNavButton(
    text: String,
    shape: RoundedCornerShape,
    rotation: Float,
    textRotation: Float,
    buttonModifier: Modifier
) {
    Box(
        modifier = Modifier
            .height(30.dp)
            .fillMaxWidth()
            .then(buttonModifier)
            .border(
                1.5.dp,
                Color.Black,
                shape = shape
            )
            .clip(shape)
            .drawWithContent {
                drawContent()
            }
            .graphicsLayer(
                rotationY = rotation
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Black, Color.Cyan),
                    startX = 0f,
                    endX = 90f
                )
            )
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxSize()
                .padding(7.dp)
                .graphicsLayer(
                    rotationY = textRotation
                ),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
    }
}

@Composable
fun CustomBaseButton(
    text: String,
    maxWidth: Float,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(maxWidth)
            .height(30.dp)
            .border(1.5.dp, Color.Black, shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(color = Color.Cyan)
            .clickable { onClick?.invoke() }
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxSize()
                .padding(7.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
    }
}