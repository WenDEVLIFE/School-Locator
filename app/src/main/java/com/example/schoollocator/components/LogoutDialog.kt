package com.example.schoollocator.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController

@Composable
fun LogoutDialog(
    navController: NavHostController,
    dialogState: MutableState<Boolean>,
    logoutState: MutableState<Boolean>,
    route: String
) {
    // This method is used for clearing the navigation controller backstack
    fun clearNavController() {
        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }

    if (dialogState.value) {
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
                        navController.navigate("Login"){
                            popUpTo(route){
                                inclusive = true
                            }
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
}