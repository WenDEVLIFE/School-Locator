package com.example.schoollocator.activity.Screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.activity.maincomponent.components.BottomNavigationBar
import com.example.schoollocator.activity.maincomponent.components.NavigationGraph


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
        navController.navigate("Login") // Navigate to login
        logoutState.value = false // Reset the logout state
    }
}

// This is for the logout diaog
@Composable
fun LogoutDialog(
    navController: NavHostController,
    dialogState: MutableState<Boolean>,
    logoutState: MutableState<Boolean>
) {

    // This method used for clearing the navigation controller backstack
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
