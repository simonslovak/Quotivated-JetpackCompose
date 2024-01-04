package com.utb.quotivated.activities

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.utb.quotivated.R
import com.utb.quotivated.ui.theme.QuotivatedTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.utb.quotivated.view_model.AppViewModel

class MainActivity : ComponentActivity() {
    private val AppViewModel by viewModels<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotivatedTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(navController, AppViewModel)
                    }
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

@Composable
fun MainScreen(navController: NavHostController, viewModel: AppViewModel) {

    LaunchedEffect(Unit) {
        viewModel.loadRandomQuote()
        viewModel.loadImageData()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                Row(
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
                                        painter = rememberAsyncImagePainter(
                                            ImageRequest.Builder(
                                                LocalContext.current
                                            ).data(data = viewModel.photo.value).apply(block = fun ImageRequest.Builder.() {

                                            }).build()
                                        ),
                                        contentDescription = "Random image",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .border(
                                                1.5.dp,
                                                Color.Black,
                                                shape = RoundedCornerShape(15.dp)
                                            )
                                            .clip(RoundedCornerShape(15.dp))
                                            .alpha(0.6f)
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
                                            painterResource(id = R.drawable.favorite),
                                            contentDescription = "Star Icon",
                                            modifier = Modifier
                                                .size(22.dp)
                                                .padding(0.dp, 2.5.dp, 0.dp, 0.dp)
                                                .clickable {},
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
                                            .clip(RoundedCornerShape(15.dp)),
                                        contentAlignment = Alignment.TopCenter
                                    ) {
                                        TextWithShadow(
                                            text = "${viewModel.quote.value?.content.toString()?: "Press the generate button to get a random quote."}",
                                            fontSize = 22
                                        )
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
                                            .clip(RoundedCornerShape(15.dp)),
                                        contentAlignment = Alignment.BottomEnd
                                    ) {
                                        TextWithShadow(
                                            text = "${viewModel.quote.value?.author.toString()?: "Unknown author"}",
                                            fontSize = 18
                                        )
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
                    CustomBaseButton(
                        "Generate image",
                        1f,
                        onClick = {
                            viewModel.loadImageData()
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 20.dp, 15.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomBaseButton(
                        "Generate quote",
                        1f,
                        onClick = {
                            viewModel.loadRandomQuote()
                        }
                    )
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

        CompositionLocalProvider(LocalViewModelStoreOwner provides LocalContext.current as ViewModelStoreOwner) {
            MainScreen(navController, AppViewModel())
        }
    }
}