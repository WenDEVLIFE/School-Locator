package com.example.schoollocator.activity.maincomponent.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun LogoutDialog(
    navController: NavHostController
) {
    // This method is used for clearing the navigation controller backstack
    fun clearNavController() {
        navController.popBackStack(navController.graph.startDestinationId, false)
    }
    val dialogState = remember { mutableStateOf(false) }
    val logoutState = remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = {
            dialogState.value = false // Dismiss the dialog
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
                    // Perform logout action
                    dialogState.value = false // Dismiss the dialog
                    logoutState.value = true  // Trigger the logout state
                    clearNavController()      // Clear the backstack
                    navController.navigate("Login") {
                        launchSingleTop = true
                        restoreState = true
                    }
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
