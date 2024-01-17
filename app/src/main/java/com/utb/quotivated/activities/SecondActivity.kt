// SecondActivity.kt
package com.utb.quotivated.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.utb.quotivated.R
import com.utb.quotivated.custom.CustomBaseButton
import com.utb.quotivated.custom.CustomNavButton
import com.utb.quotivated.custom.RoundedBox
import com.utb.quotivated.custom.TextWithShadow
import com.utb.quotivated.data_classes.QuoteData
import com.utb.quotivated.ui.theme.QuotivatedTheme
import com.utb.quotivated.view_model.AppViewModel
import kotlinx.coroutines.launch

class SecondActivity : ComponentActivity() {
    private val appViewModel by viewModels<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotivatedTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SecondScreen(navController = rememberNavController(), appViewModel)
                }
            }
        }
    }
}

@Composable
fun SecondScreen(navController: NavHostController, viewModel: AppViewModel) {
    val scope = rememberCoroutineScope()
    var currentIndex by rememberSaveable { mutableStateOf(0) }
    val savedQuotes by viewModel.savedQuotes.collectAsState(initial = emptyList())

    LaunchedEffect(rememberUpdatedState(currentIndex)) {
        if (savedQuotes.isNotEmpty()) {
            currentIndex = savedQuotes.size - 1
            viewModel.setLoadedData(
                QuoteData(
                    text = savedQuotes[currentIndex].text,
                    author = savedQuotes[currentIndex].author,
                    image = savedQuotes[currentIndex].image
                )
            )
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(0.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(15.dp, 20.dp, 15.dp, 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().aspectRatio(1f)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(
                                            ImageRequest.Builder(LocalContext.current)
                                                .data(data = viewModel.quoteData.value?.image ?: byteArrayOf())
                                                .apply {
                                                }.build()
                                        ),
                                        contentDescription = "Random image",
                                        modifier = Modifier.fillMaxSize()
                                            .border(1.5.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                                            .clip(RoundedCornerShape(15.dp))
                                            .alpha(0.6f)
                                    )
                                    Box(
                                        modifier = Modifier.size(45.dp)
                                            .align(Alignment.TopEnd)
                                            .border(1.5.dp, Color.Transparent, shape = RoundedCornerShape(22.5.dp))
                                            .clip(RoundedCornerShape(22.5.dp))
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
                                            }
                                            .clickable {
                                                scope.launch {
                                                    if (savedQuotes.isNotEmpty()) {
                                                        val updatedQuotes = savedQuotes.toMutableList().apply {
                                                            removeAt(currentIndex)
                                                        }
                                                        currentIndex = if (updatedQuotes.isNotEmpty()) {
                                                            (currentIndex + 1) % updatedQuotes.size
                                                        } else {
                                                            0
                                                        }
                                                        if (updatedQuotes.isNotEmpty()) {
                                                            viewModel.setLoadedData(updatedQuotes[currentIndex])
                                                        } else {
                                                            viewModel.setLoadedData(
                                                                QuoteData(
                                                                    text = "Press the generate button to get a random quote.",
                                                                    author = "Unknown author",
                                                                    image = byteArrayOf()
                                                                )
                                                            )
                                                        }
                                                        viewModel.saveQuotes(updatedQuotes)
                                                    }
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painterResource(id = R.drawable.favorite_filled),
                                            contentDescription = "Star Icon",
                                            modifier = Modifier.size(22.dp).padding(0.dp, 2.5.dp, 0.dp, 0.dp),
                                            colorFilter = ColorFilter.tint(Color.Black)
                                        )
                                    }
                                    Box(
                                        modifier = Modifier.fillMaxSize().padding(35.dp, 45.dp, 35.dp, 35.dp)
                                            .background(brush = Brush.radialGradient(
                                                colors = listOf(Color.Cyan, Color.Transparent),
                                                radius = 0.5f
                                            ))
                                            .clip(RoundedCornerShape(15.dp)),
                                        contentAlignment = Alignment.TopCenter
                                    ) {
                                        TextWithShadow(
                                            text = "${viewModel.quoteData.value?.text ?: "No favorite quote and image"}",
                                            fontSize = 26
                                        )
                                    }
                                    Box(
                                        modifier = Modifier.fillMaxSize().padding(35.dp)
                                            .background(brush = Brush.radialGradient(
                                                colors = listOf(Color.Cyan, Color.Transparent),
                                                radius = 0.5f
                                                )
                                            )
                                            .clip(RoundedCornerShape(15.dp)),
                                        contentAlignment = Alignment.BottomEnd
                                    ) {
                                        TextWithShadow(
                                            text = "${viewModel.quoteData.value?.author ?: "No favorite author"}",
                                            fontSize = 22
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(15.dp, 30.dp, 15.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomBaseButton(
                        "Previous favorite",
                        1f,
                        onClick = {
                            if (savedQuotes.isNotEmpty()) {
                                currentIndex = (currentIndex - 1 + savedQuotes.size) % savedQuotes.size
                                if (currentIndex >= 0 && currentIndex < savedQuotes.size) {
                                    viewModel.setLoadedData(savedQuotes[currentIndex])
                                }
                            }
                        }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(15.dp, 20.dp, 15.dp, 35.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomBaseButton(
                        "Next favorite",
                        1f,
                        onClick = {
                            if (savedQuotes.isNotEmpty()) {
                                currentIndex = (currentIndex + 1) % savedQuotes.size
                                viewModel.setLoadedData(savedQuotes[currentIndex])
                            }
                        }
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1f).height(5.dp).background(color = Color.Transparent)
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(0.5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomNavButton(
                        text = "Back",
                        shape = RoundedCornerShape(5.dp),
                        rotation = 0f,
                        textRotation = 0f,
                        buttonModifier = Modifier
                            .weight(0.35f)
                            .clickable {
                                navController.navigate("main")
                            }
                    )
                    Spacer(
                        modifier = Modifier.weight(0.005f)
                    )
                    RoundedBox(
                        boxModifier = Modifier.weight(0.2f),
                        imageId = R.drawable.favorite_empty
                    )
                    Spacer(
                        modifier = Modifier.weight(0.355f)
                    )
                }
            }
        }
    }
}
