package com.example.schoollocator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoollocator.ui.theme.SchoolLocatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchoolLocatorTheme {
                LoadingScreen()
            }
        }
    }
}

@Composable
fun Splash(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Hello, text",
            modifier = Modifier.padding(16.dp),
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
            color = Color.White
        )
        CircularProgressIndicator(
            color = Color.Blue,
            modifier = Modifier.align(Alignment.Center)
        )

    }
}

@Composable
fun LoadingScreen() {
    Splash()
}

@Preview
@Composable
fun GreetingPreview() {
    SchoolLocatorTheme {
        Splash()
    }
}