package com.utb.quotivated

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.utb.quotivated.ui.theme.QuotivatedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotivatedTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main") {
                    composable("main") { MainScreen(navController) }
                    composable("second") { SecondScreen(navController) }
                    composable("third") { ThirdScreen(navController) }
                }
            }
        }
    }
}
@Composable
fun RoundedBox(boxModifier: Modifier) {
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
            painter = painterResource(id = R.drawable.home),
            contentDescription = "Home Icon",
            modifier = Modifier
                .size(20.dp)
        )
    }
}
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
                    colors = listOf(Color.DarkGray, Color.Cyan),
                    startX = 0f,
                    endX = 80f
                )
            )
            .clickable { }
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
fun CustomBaseButton(text: String, maxWidth: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth(maxWidth)
            .height(30.dp)
            .border(1.5.dp, Color.Black, shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(color = Color.Cyan)
            .clickable { }
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

@Composable
fun TextWithShadow(text: String) {
    Text(
        text = text,
        color = Color.Cyan,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        modifier = Modifier
            .wrapContentSize()
            .offset((-0.5).dp, (-0.5).dp)
            .blur(1.5.dp)
    )
    Text(
        text = text,
        color = Color.Cyan,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        modifier = Modifier
            .wrapContentSize()
            .offset(1.5.dp, 1.5.dp)
            .blur(1.5.dp)
    )
    Text(
        text = text,
        color = Color.Black,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        modifier = Modifier
            .wrapContentSize()
    )
}

@Composable
fun MainScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Gray
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 20.dp, 15.dp, 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.tree),
                                        contentDescription = "Tree",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .border(
                                                1.5.dp,
                                                Color.Black,
                                                shape = RoundedCornerShape(15.dp)
                                            )
                                            .clip(RoundedCornerShape(15.dp))
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(45.dp)
                                            .align(Alignment.TopEnd)
                                            .border(
                                                1.5.dp,
                                                Color.Transparent,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                            .clip(RoundedCornerShape(20.dp))
                                            .padding(2.5.dp)
                                            .drawBehind {
                                                val gradient = Brush.radialGradient(
                                                    colors = listOf(Color.Cyan, Color.Transparent),
                                                    center = center,
                                                    radius = size.width / 2
                                                )
                                                drawRoundRect(
                                                    brush = gradient,
                                                    size = size.copy(height = size.height),
                                                )
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.favorite),
                                            contentDescription = "Star Icon",
                                            modifier = Modifier
                                                .size(22.dp)
                                                .clickable { /* Handle click event if needed */ },
                                            colorFilter = ColorFilter.tint(Color.Black)
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(35.dp, 45.dp, 35.dp, 35.dp)
                                            .background(
                                                brush = Brush.radialGradient(
                                                    colors = listOf(Color.Cyan, Color.Transparent),
                                                    radius = 0.5f
                                                )
                                            )
                                            .clip(RoundedCornerShape(15.dp))
                                            .clickable { /* Handle click event if needed */ },
                                        contentAlignment = Alignment.TopCenter
                                    ) {
                                        TextWithShadow("\"When one door of happiness closes, another opens; but often we look so long in disappointment and bitterness at the closed door that we do not expectantly look for and therefore see with pleasure and gratitude the one which has been opened for us.\"")
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(35.dp)
                                            .background(
                                                brush = Brush.radialGradient(
                                                    colors = listOf(Color.Cyan, Color.Transparent),
                                                    radius = 0.5f
                                                )
                                            )
                                            .clip(RoundedCornerShape(15.dp))
                                            .clickable { /* Handle click event if needed */ },
                                        contentAlignment = Alignment.BottomEnd
                                    ) {
                                        TextWithShadow("- Author")
                                    }
                                }
                            }

                        }

                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 30.dp, 15.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomBaseButton("Generate image", 0.45f)
                    CustomBaseButton("Take photo", 0.90f)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 20.dp, 15.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomBaseButton("Generate quote", 1f)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 35.dp, 15.dp, 25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomNavButton(
                        text = "Statistics",
                        shape = RoundedCornerShape(15.dp, 5.dp, 5.dp, 15.dp),
                        rotation = 0f,
                        textRotation = 0f,
                        buttonModifier = Modifier
                            .weight(0.35f)
                    )

                    Spacer(modifier = Modifier
                            .weight(0.05f))

                    RoundedBox(
                        boxModifier = Modifier
                            .weight(0.2f)
                    )

                    Spacer(modifier = Modifier
                        .weight(0.05f))

                    CustomNavButton(
                        text = "Favorites",
                        shape = RoundedCornerShape(5.dp, 15.dp, 15.dp, 5.dp),
                        rotation = 180f,
                        textRotation = 180f,
                        buttonModifier = Modifier
                            .weight(0.35f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    QuotivatedTheme {
        val navController = rememberNavController()

        MainScreen(navController)
    }
}
