package com.example.schoollocator.activity.Screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize
import com.example.schoollocator.R
import com.example.schoollocator.graphs.AppNavigation
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.SchoolLocatorTheme
import com.example.schoollocator.viewmodel.SessionViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchoolLocatorTheme {
                
                // assign or link the session view model here
                val sessionViewModel: SessionViewModel = viewModel(factory = SessionViewModelFactory(application))
                val navController = rememberNavController()
                LoadingScreen(navController = navController)
                AppNavigation(navController = navController, sessionViewModel = sessionViewModel)
            }
        }
    }
}
@Composable
fun Splash(navController: NavHostController, modifier: Modifier = Modifier) {
    val screenSize = getScreenSize()
    var progress by remember { mutableStateOf(0f) }
    val context = LocalContext.current

    // This is for the function of loading of circular indicator
    LaunchedEffect(Unit) {
        while (progress < 1f) {
            progress += 0.1f
            delay(50) // Adjust the delay to control the speed of the progress
        }

        if (progress >= 1) {

            // This is for the navigation of the screen
            navController.navigate("Login"){
                    popUpTo("SplashScreen") {
                        inclusive = true
                    }

            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(Green1) // Apply the Green1 color here
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {


            Image(
                painter = painterResource(id = R.drawable.school_locator),
                contentDescription = "Icon",
                modifier = Modifier.size(
                    width = if (screenSize == ScreenSize.SMALL) 150.dp else 204.dp,
                    height = if (screenSize == ScreenSize.SMALL) 150.dp else 204.dp
                )// Adjust the size as needed
            )

            Text(
                text = "School Locator",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = Color.White,
                fontSize = if (screenSize == ScreenSize.SMALL) 30.sp else 40.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 5.dp,
                progress = progress,
                modifier = Modifier.size(
                    width = if (screenSize == ScreenSize.SMALL) 60.dp else 70.dp,
                    height = if (screenSize == ScreenSize.SMALL) 60.dp else 70.dp
                )
            )
        }
    }
}

@Composable
fun LoadingScreen(navController: NavHostController) {
    Splash(navController = navController, modifier = Modifier.fillMaxWidth())
}


// This is for the preview of the Splash screen
@Preview
@Composable
fun GreetingPreview() {
    SchoolLocatorTheme {
        Splash(navController = rememberNavController(), modifier = Modifier.fillMaxWidth())
    }
}