// MainActivity.kt
package com.utb.quotivated.activities

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.utb.quotivated.R
import com.utb.quotivated.ui.theme.QuotivatedTheme
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.utb.quotivated.custom.CustomBaseButton
import com.utb.quotivated.custom.CustomNavButton
import com.utb.quotivated.custom.TextWithShadow
import com.utb.quotivated.custom.RoundedBox
import com.utb.quotivated.data_classes.QuoteData
import com.utb.quotivated.view_model.AppViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val AppViewModel by viewModels<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            QuotivatedTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(navController, AppViewModel)
                    }
                    composable("second") { SecondScreen(navController, AppViewModel) }
                    composable("third") { ThirdScreen(navController, AppViewModel) }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController, viewModel: AppViewModel) {
    val scope = rememberCoroutineScope()
    val savedQuotes by viewModel.savedQuotes.collectAsState(initial = emptyList())
    var isDataLoaded by remember { mutableStateOf(false) }
    
    var image_count by remember { mutableStateOf(0) }
    var quote_count by remember { mutableStateOf(0) }
    
    image_count = viewModel.getImageCount.collectAsState(initial = 0).value
    quote_count = viewModel.getQuoteCount.collectAsState(initial = 0).value

    LaunchedEffect(isDataLoaded) {
        if (!isDataLoaded) {
            val loadedQuote = viewModel.quote.value
            val loadedPhoto = viewModel.photo.value

            if (loadedQuote != null && loadedPhoto != null) {
                viewModel.setLoadedData(loadedQuote, loadedPhoto)
            } else {
                viewModel.loadRandomQuote()
                viewModel.loadImageData()
            }
            isDataLoaded = true
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
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
                                                shape = RoundedCornerShape(22.5.dp)
                                            )
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
                                                viewModel.isFavorite = !viewModel.isFavorite
                                                scope.launch {
                                                    val quote = viewModel.quote.value
                                                    val photo = viewModel.photo.value
                                                    if (quote != null && photo != null) {
                                                        val newQuote = QuoteData(
                                                            text = quote.content ?: "",
                                                            author = quote.author ?: "",
                                                            image = photo
                                                        )
                                                        val updatedQuotes = if (viewModel.isFavorite) {
                                                            val newList = savedQuotes + newQuote
                                                            Log.d("Quotivated", "Adding quote to the list. New list size: ${newList.size}")
                                                            newList
                                                        } else {
                                                            val newList = savedQuotes.dropLast(1)
                                                            Log.d("Quotivated", "Removing the last quote from the list. New list size: ${newList.size}")
                                                            newList
                                                        }
                                                        viewModel.saveQuotes(updatedQuotes)
                                                    }
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painterResource(id = if (viewModel.isFavorite) R.drawable.favorite_filled else R.drawable.favorite_empty),
                                            contentDescription = "Star Icon",
                                            modifier = Modifier
                                                .size(22.dp)
                                                .padding(0.dp, 2.5.dp, 0.dp, 0.dp),
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
                                            fontSize = 26
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
                                            fontSize = 22
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
                            viewModel.isFavorite = false
                            image_count++
                            viewModel.viewModelScope.launch {
                                viewModel.saveGenerateImageCount(image_count)
                            }
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 20.dp, 15.dp, 35.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomBaseButton(
                        "Generate quote",
                        1f,
                        onClick = {
                            viewModel.loadRandomQuote()
                            viewModel.isFavorite = false
                            quote_count++
                            viewModel.viewModelScope.launch {
                                viewModel.saveGenerateQuoteCount(quote_count)
                            }
                        }
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(5.dp)
                        .background(color = Color.Transparent)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomNavButton(
                        text = "Statistics",
                        shape = RoundedCornerShape(5.dp),
                        rotation = 0f,
                        textRotation = 0f,
                        buttonModifier = Modifier
                            .weight(0.35f)
                            .clickable {
                                navController.navigate("third")
                            }
                    )
                    Spacer(modifier = Modifier
                        .weight(0.005f)
                    )
                    RoundedBox(
                        boxModifier = Modifier
                            .weight(0.2f),
                        imageId = R.drawable.home
                    )
                    Spacer(modifier = Modifier
                        .weight(0.005f)
                    )
                    CustomNavButton(
                        text = "Favorites",
                        shape = RoundedCornerShape(5.dp),
                        rotation = 180f,
                        textRotation = 180f,
                        buttonModifier = Modifier
                            .weight(0.35f)
                            .clickable {
                                navController.navigate("second")
                            }
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
            MainScreen(navController, AppViewModel(Application()))
        }
    }
}
