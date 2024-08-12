package com.example.schoollocator.activity.maincomponent.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.BuildConfig
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.WhiteCus
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.ui.theme.materialLightGreen
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.WellKnownTileServer


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
    NavigationBar(containerColor = materialLightGreen) {
        val selectedItem = remember { mutableStateOf("Map") }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        // This is the first NavigationBarItem  or map
        NavigationBarItem(
            icon = {
                val iconColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Map")  Color.Green else materialGreen
                )
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Map",
                    tint = iconColor
                )
            },
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Map") materialGreen else materialGreen
                )
                Text("Map", color = textColor)
            },
            // The select value to set the item
            selected = selectedItem.value == "Map",
            onClick = {
                selectedItem.value = "Map"
                navController.navigate("Map") {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                unselectedIconColor =  materialGreen,
                selectedTextColor =  materialGreen,
                unselectedTextColor =  materialGreen,
                indicatorColor = Green1
            )
        )

        // This is  the second NavigationBarItem or home
        NavigationBarItem(
            icon = {
                val iconColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Home")  Color.Green else materialGreen
                )
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = iconColor
                )
            },
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Home")  materialGreen else materialGreen
                )
                Text("Home", color = textColor)
            },

            // The select value to set the item
            selected = selectedItem.value == "Home",
            onClick = {
                selectedItem.value = "Home"

            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                unselectedIconColor =  Color.Black,
                selectedTextColor =  materialGreen,
                unselectedTextColor =  materialGreen,
                indicatorColor = Green1
            )
        )

        // Add more NavigationBarItems as needed
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, contentPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "Map") {
        composable("Map") {
            MainMap(modifier = Modifier.padding(contentPadding))

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