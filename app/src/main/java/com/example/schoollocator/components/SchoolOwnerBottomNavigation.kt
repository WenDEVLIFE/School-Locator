package com.example.schoollocator.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.darkblue
import com.example.schoollocator.ui.theme.darkblue800
import com.example.schoollocator.ui.theme.white900
import com.example.schoollocator.viewmodel.SessionViewModel

// TODO : Create SchoolOwnerBottomNavigation composable function
@Composable
fun OwnerBottomNavigation(
    navController: NavHostController,
    dialogState: MutableState<Boolean>
) {
    NavigationBar(containerColor = darkblue) {
        val sessionViewModel: SessionViewModel = viewModel()
        val selectedItem = remember { mutableStateOf("Schools") }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val username = sessionViewModel.username.value
        val email = sessionViewModel.email.value
        val role = sessionViewModel.role.value

        // Update selectedItem based on current route
        LaunchedEffect(currentRoute) {
            selectedItem.value = currentRoute ?: "Schools"
        }

        // Map NavigationBarItem
        NavigationBarItem(
            icon = {
                val iconColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Schools") white900 else white900
                )
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Schools",
                    tint = iconColor
                )
            },
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Schools") darkblue800 else darkblue800
                )
                Text("Schools", color = textColor)
            },
            selected = selectedItem.value == "Schools",
            onClick = {
                selectedItem.value = "Schools"

                navController.navigate("School") {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = darkblue800,
                unselectedIconColor = darkblue800,
                selectedTextColor = white900,
                unselectedTextColor = white900,
                indicatorColor = darkblue800
            )
        )

        // Home NavigationBarItem
        NavigationBarItem(
            icon = {
                val iconColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Users") white900 else white900
                )
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Users",
                    tint = iconColor
                )
            },
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Users") darkblue800 else darkblue800
                )
                Text("Users", color = textColor)
            },
            selected = selectedItem.value == "Users",
            onClick = {
                selectedItem.value = "Users"
                navController.navigate("User") {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = darkblue800,
                unselectedIconColor = darkblue800,
                selectedTextColor = white900,
                unselectedTextColor = white900,
                indicatorColor = darkblue800
            )
        )

        // Favorite NavigationBarItem
        NavigationBarItem(
            icon = {
                val iconColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Account") white900 else white900
                )
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Account",
                    tint = iconColor
                )
            },
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Account") darkblue800 else darkblue800
                )
                Text("Account", color = textColor)
            },
            selected = selectedItem.value == "Account",
            onClick = {
                selectedItem.value = "Account"
                navController.navigate("Home") {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = darkblue800,
                unselectedIconColor = darkblue800,
                selectedTextColor = white900,
                unselectedTextColor = white900,
                indicatorColor = darkblue800
            )
        )

        // Logout NavigationBarItem
        NavigationBarItem(
            icon = {
                val iconColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Logout") white900 else white900
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_power_settings_new_24),
                    contentDescription = "Logout",
                    tint = iconColor
                )
            },
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Logout") darkblue800 else darkblue800
                )
                Text("Logout", color = textColor)
            },
            selected = selectedItem.value == "Logout",
            onClick = {
                selectedItem.value = "Logout"
                dialogState.value = true // Show the dialog
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = darkblue800,
                unselectedIconColor = darkblue800,
                selectedTextColor = white900,
                unselectedTextColor = white900,
                indicatorColor = darkblue800
            )
        )
    }

}
