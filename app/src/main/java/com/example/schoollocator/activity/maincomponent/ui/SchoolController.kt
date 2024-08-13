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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.R
import com.example.schoollocator.activity.defaultcomponent.AppNavigation1
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.ui.theme.materialLightGreen


@Composable
fun SchoolController() {
    val navController: NavHostController = rememberNavController() // Initialize navController
    val dialogState = remember { mutableStateOf(false) } // Initialize dialog state
    val logoutState = remember { mutableStateOf(false) } // Initialize logout state

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, dialogState = dialogState)
        }
    ) { contentPadding ->
        NavigationGraph(navController = navController, contentPadding = contentPadding, dialogState = dialogState, logoutState = logoutState)
    }

    // This is for the dialog state to show the dialog
    if (dialogState.value) {
        LogoutDialog(navController = navController ,dialogState = dialogState, logoutState = logoutState)
    }

    // This is for the logout state
    if (logoutState.value) {
        AppNavigation1()
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

            // This is for the label
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Map") materialGreen else materialGreen
                )
                Text("Map", color = textColor)
            },

            // This is for the selected value
            selected = selectedItem.value == "Map",

            // This is for the onClick
            onClick = {
                selectedItem.value = "Map"
                navController.navigate("Map") {
                    launchSingleTop = true
                    restoreState = true
                }
            },

            // the colors of the navigation item
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

            //
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Home") materialGreen else materialGreen
                )
                Text("Home", color = textColor)
            },

            // This is for the selected value
            selected = selectedItem.value == "Home",

            // This is for the onClick
            onClick = {
                selectedItem.value = "Home"
            },

            // This is for the colors
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

            // This is for the iconns
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

            // This is for the label
            label = {
                val textColor by animateColorAsState(
                    targetValue = if (selectedItem.value == "Logout") materialGreen else materialGreen
                )
                Text("Logout", color = textColor)
            },

            // This is for the select value
            selected = selectedItem.value == "Logout",

            // This is for the onClick
            onClick = {
                selectedItem.value = "Logout"
                dialogState.value = true // Show the dialog
            },

            // This is for colors
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

// This is for the logout diaog
@Composable
fun LogoutDialog(
    navController: NavHostController,
    dialogState: MutableState<Boolean>,
    logoutState: MutableState<Boolean>
) {
    fun clearNavController() {
        navController.popBackStack(navController.graph.startDestinationId, false)
    }
    AlertDialog(
        onDismissRequest = {
            dialogState.value = false
                           },

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
                    dialogState.value = false // Dismiss the dialog
                    logoutState.value = true
                    clearNavController()
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
fun NavigationGraph(navController: NavHostController, contentPadding: PaddingValues, dialogState: MutableState<Boolean>, logoutState: MutableState<Boolean>) {
    NavHost(navController = navController, startDestination = "Map") {

        // Map Composable
        composable("Map") {
            MainMap(modifier = Modifier.padding(contentPadding))

        }

        // Home Composable
        composable("Home") {

        }
        composable("Logout") {
            LogoutDialog(
                dialogState = dialogState,
                logoutState = logoutState,
                navController = navController
            )
        }


    }
}


// This is for the preview
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
