package com.example.schoollocator.activity.maincomponent.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.ui.theme.Green1


@Composable
fun SchoolController() {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { contentPadding ->
        NavigationGraph(navController = navController, contentPadding = contentPadding)
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Map",
                )
            },
            label = { Text("Map", color = Color.Black) },
            selected = true,
            onClick = {
                navController.navigate("Map")
            },

                    colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Green, // Change to desired color
            unselectedIconColor = Color.Black, // Change to desired color
            selectedTextColor = Color.Green, // Change to desired color
            unselectedTextColor = Color.Black, // Change to desired color
            indicatorColor = Green1 // Change to desired color
        )
        )
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, contentPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "Map") {
        composable("Map") {
            Text("Map Screen", modifier = Modifier.padding(contentPadding))
        }
        // Add other composable destinations here
    }
}

@Preview
@Composable
fun SchoolControllerPreview() {
    SchoolController()
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}