// ThirdActivity.kt
package com.utb.quotivated.activities

import android.app.Application
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.utb.quotivated.R
import com.utb.quotivated.custom.CustomBaseButton
import com.utb.quotivated.custom.CustomNavButton
import com.utb.quotivated.custom.RoundedBox
import com.utb.quotivated.custom.TextWithShadow
import com.utb.quotivated.ui.theme.QuotivatedTheme
import com.utb.quotivated.view_model.AppViewModel
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.toList

class ThirdActivity : ComponentActivity() {
    private val AppViewModel by viewModels<AppViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotivatedTheme {
                // You can customize the color and appearance of the Surface as needed
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ThirdScreen(navController = rememberNavController(), AppViewModel)
                }
            }
        }
    }
}

@Composable
fun ThirdScreen(navController: NavHostController, viewModel: AppViewModel) {

    val scope = rememberCoroutineScope()
    val savedQuotesSize by viewModel.savedQuotes.collectAsState(initial = emptyList())

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
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .border(
                                                1.5.dp,
                                                Color.Black,
                                                shape = RoundedCornerShape(15.dp)
                                            )
                                            .clip(RoundedCornerShape(15.dp))
                                            .alpha(0.6f)
                                            .background(color=Color.Gray)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.statistics_background),
                                            contentDescription = "Random image",
                                            modifier = Modifier.fillMaxSize()
                                                .border(1.5.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                                                .clip(RoundedCornerShape(15.dp))
                                                .alpha(0.6f)
                                        )
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
                                            contentAlignment = Alignment.TopStart
                                        ) {
                                            TextWithShadow(
                                                text = "" +
                                                        "Favorites stored: ${savedQuotesSize.size}\n\n\n" +
                                                        "Quotes generated: \n\n\n" +
                                                        "Images generated: ",
                                                fontSize = 30
                                            )
                                        }
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
                        "Reset statistics",
                        1f,
                        onClick = {

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
                        "Reset favorites",
                        1f,
                        onClick = {

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
                    Spacer(modifier = Modifier
                        .weight(0.355f)
                    )

                    RoundedBox(
                        boxModifier = Modifier
                            .weight(0.2f),
                        imageId = R.drawable.statistics
                    )

                    Spacer(modifier = Modifier
                        .weight(0.005f))

                    CustomNavButton(
                        text = "Back",
                        shape = RoundedCornerShape(5.dp),
                        rotation = 180f,
                        textRotation = 180f,
                        buttonModifier = Modifier
                            .weight(0.35f)
                            .clickable {
                                navController.navigate("main")
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdScreenPreview() {
    QuotivatedTheme {
        ThirdScreen(navController = rememberNavController(), AppViewModel(Application()))
    }
}
