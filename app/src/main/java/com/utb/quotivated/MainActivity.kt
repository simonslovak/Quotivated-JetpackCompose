package com.utb.quotivated

import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

                // Set up NavHost with different composable destinations
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
fun LeftRoundedTriangleButton() {
    Box(
        modifier = Modifier
            .size(130.dp, 40.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(20.dp, 10.dp, 10.dp, 20.dp))
            .clip(RoundedCornerShape(20.dp, 10.dp, 10.dp, 20.dp))
            .background(
                color = Color.Cyan,
            )
    ) {
        Text(
            text = "Statistics",
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RoundedBox() {
    Box(
        modifier = Modifier
            .size(40.dp, 40.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = Color.Cyan,
            )
    )
}

@Composable
fun RightRoundedTriangleButton() {
    Box(
        modifier = Modifier
            .size(130.dp, 40.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp, 20.dp, 20.dp, 10.dp))
            .clip(RoundedCornerShape(10.dp, 20.dp, 20.dp, 10.dp))
            .background(
                color = Color.Cyan,
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Favorites",
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Rest of the content

            Spacer(modifier = Modifier.weight(1f)) // Flexible spacer

            // Box composable to overlay Row with Square
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // Row layout for buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LeftRoundedTriangleButton()
                    // Button to navigate to SecondScreen
//                    Button(
//                        onClick = { navController.navigate("second") },
//                        modifier = Modifier.width(125.dp)
//                    ) {
//                        Text("Stats")
//                    }

                    // Square shape (background with primary color)
                    RoundedBox()

                    RightRoundedTriangleButton()
                    // Button to navigate to ThirdScreen
//                    Button(
//                        onClick = { navController.navigate("third") },
//                        modifier = Modifier.width(125.dp)
//                    ) {
//                        Text("Favorites")
//                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuotivatedTheme {
        val navController = rememberNavController()

        // Call the MainScreen composable
        MainScreen(navController)
    }
}
