package com.example.schoollocator.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.ui.theme.materialLightGreen
import com.example.schoollocator.viewmodel.SessionViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    dialogState: MutableState<Boolean>
) {
    NavigationBar(containerColor = materialLightGreen) {
        val sessionViewModel: SessionViewModel = viewModel()
        val selectedItem = remember { mutableStateOf("Map") }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val username = sessionViewModel.username.value
        val email = sessionViewModel.email.value
        val role = sessionViewModel.role.value

        // Update selectedItem based on current route
        LaunchedEffect(currentRoute) {
            selectedItem.value = currentRoute ?: "Map"
        }

        // Map NavigationBarItem
        NavigationBarItem(
            icon = {
                val iconColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Map") Color.Green else materialGreen
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
            selected = selectedItem.value == "Map",
            onClick = {
                selectedItem.value = "Map"

                val map = hashMapOf(
                    "username" to username,
                    "email" to email,
                    "role" to role
                )

                // Serialize map to JSON string
                val jsonString = Json.encodeToString(map)

                navController.navigate("Map/$jsonString") {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                unselectedIconColor = materialGreen,
                selectedTextColor = materialGreen,
                unselectedTextColor = materialGreen,
                indicatorColor = Green1
            )
        )

        // Home NavigationBarItem
        NavigationBarItem(
            icon = {
                val iconColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Home") Color.Green else materialGreen
                )
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Home",
                    tint = iconColor
                )
            },
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Home") materialGreen else materialGreen
                )
                Text("Menu", color = textColor)
            },
            selected = selectedItem.value == "Home",
            onClick = {
                selectedItem.value = "Home"
                navController.navigate("Home") {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                unselectedIconColor = Color.Black,
                selectedTextColor = materialGreen,
                unselectedTextColor = materialGreen,
                indicatorColor = Green1
            )
        )

        // Favorite NavigationBarItem
        NavigationBarItem(
            icon = {
                val iconColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Favorite") Color.Green else materialGreen
                )
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = iconColor
                )
            },
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Favorites") materialGreen else materialGreen
                )
                Text("Favorite", color = textColor)
            },
            selected = selectedItem.value == "Favorites",
            onClick = {
                selectedItem.value = "Favorites"
                navController.navigate("Favorites") {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                unselectedIconColor = Color.Black,
                selectedTextColor = materialGreen,
                unselectedTextColor = materialGreen,
                indicatorColor = Green1
            )
        )

        // Logout NavigationBarItem
        NavigationBarItem(
            icon = {
                val iconColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Logout") Color.Green else materialGreen
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_power_settings_new_24),
                    contentDescription = "Logout",
                    tint = iconColor
                )
            },
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Logout") materialGreen else materialGreen
                )
                Text("Logout", color = textColor)
            },
            selected = selectedItem.value == "Logout",
            onClick = {
                selectedItem.value = "Logout"
                dialogState.value = true // Show the dialog
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                unselectedIconColor = Color.Black,
                selectedTextColor = materialGreen,
                unselectedTextColor = materialGreen,
                indicatorColor = Green1
            )
        )
    }
}
