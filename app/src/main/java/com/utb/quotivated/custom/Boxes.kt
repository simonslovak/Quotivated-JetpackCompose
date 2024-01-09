package com.utb.quotivated.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.utb.quotivated.R

@Composable
fun RoundedBox(
    boxModifier: Modifier,
    imageId: Int
) {
    Box(
        modifier = Modifier
            .size(30.dp, 30.dp)
            .then(boxModifier)
            .border(1.5.dp, Color.Black, shape = RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
            .background(
                color = Color.Cyan,
            ),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Icon",
            modifier = Modifier
                .size(20.dp)
        )
    }
}