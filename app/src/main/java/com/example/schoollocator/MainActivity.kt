package com.example.schoollocator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.SchoolLocatorTheme
import kotlinx.coroutines.delay

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
    var progress by remember { mutableStateOf(0f) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        while (progress < 1f) {
            progress += 0.01f
            delay(50) // Adjust the delay to control the speed of the progress

        }

        if (progress >= 1) {
            Toast.makeText(context, "Loading complete", Toast.LENGTH_SHORT).show()
        }

    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Hello, text",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = Color.White,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )

            Spacer(modifier = Modifier.height(16.dp))

            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 5.dp,
                progress = progress
            )
        }
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
        Splash(modifier = Modifier.fillMaxWidth().
        background(Green1))
    }
}