package com.utb.quotivated

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.utb.quotivated.ui.theme.QuotivatedTheme

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotivatedTheme {
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
                        Icon(imageVector = Icons.Default.ThumbUp, contentDescription = null, tint = Color.Green, modifier = Modifier.size(48.dp))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Third Screen", style = MaterialTheme.typography.labelMedium, color = Color.Green)
                    }
                }
            }
        }
    }
}

@Composable
fun ThirdScreen(navController: NavHostController) {
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
            Icon(imageVector = Icons.Default.ThumbUp, contentDescription = null, tint = Color.Green, modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Third Screen", style = MaterialTheme.typography.labelMedium, color = Color.Green)

            // Example content for ThirdScreen
            Spacer(modifier = Modifier.height(16.dp))
            Text("This is the content of the third screen.")

            // Button to navigate back to the main screen
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("main") }) {
                Text("Go back to Main Screen")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ThirdScreenPreview() {
    QuotivatedTheme {
        ThirdActivityContent()
    }
}

@Composable
fun ThirdActivityContent() {
    // You can use this composable for preview or testing purposes.
    ThirdActivity()
}
