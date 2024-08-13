package com.example.schoollocator.activity.maincomponent.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.R
import com.example.schoollocator.activity.defaultcomponent.LoginForm1
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.ui.theme.materialLightGreen


@Composable
fun SchoolController() {
    val navController: NavHostController = rememberNavController()
    val dialogState = remember { mutableStateOf(false) } // Initialize dialog state

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, dialogState = dialogState)
        }
    ) { contentPadding ->
        NavigationGraph(navController = navController, contentPadding = contentPadding, dialogState = dialogState)
    }

    if (dialogState.value) {
        LogoutDialog(navController = navController, dialogState = dialogState)
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, dialogState: MutableState<Boolean>) {
    NavigationBar(containerColor = materialLightGreen) {
        val selectedItem = remember { mutableStateOf("Map") }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

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
                navController.navigate("Map") {
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
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = iconColor
                )
            },
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Home") materialGreen else materialGreen
                )
                Text("Home", color = textColor)
            },
            selected = selectedItem.value == "Home",
            onClick = {
                selectedItem.value = "Home"
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

@Composable
fun LogoutDialog(navController: NavHostController, dialogState: MutableState<Boolean>) {
    AlertDialog(
        onDismissRequest = { dialogState.value = false },
        title = {
            Text(text = "Logout")
        },
        text = {
            Text(text = "Are you sure you want to logout?")
        },
        confirmButton = {
            Button(
                onClick = {
                    // Clear the token
                    // Navigate to login
                    navController.navigate("login") {
                        launchSingleTop = true
                        restoreState = true
                    }
                    dialogState.value = false // Dismiss the dialog
                }
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    dialogState.value = false // Dismiss the dialog
                }
            ) {
                Text("No")
            }
        }
    )
}

@Composable
fun NavigationGraph(navController: NavHostController, contentPadding: PaddingValues, dialogState: MutableState<Boolean>) {
    NavHost(navController = navController, startDestination = "Map") {
        composable("Map") {
            MainMap(modifier = Modifier.padding(contentPadding))

        }
        composable("Home") {

        }
        composable("Logout") {
            LogoutDialog(navController = navController, dialogState = dialogState)
        }
        composable("login") {
            LoginForm1(navController = navController)
        }

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
    BottomNavigationBar(navController = rememberNavController() , dialogState = remember { mutableStateOf(false) })
}
