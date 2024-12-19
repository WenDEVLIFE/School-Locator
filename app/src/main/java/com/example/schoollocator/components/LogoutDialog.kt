package com.example.schoollocator.components

import android.app.Application
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.viewmodel.SessionViewModel

@Composable
fun LogoutDialog(
    navController: NavHostController,
    dialogState: MutableState<Boolean>,
    logoutState: MutableState<Boolean>,
    route: String,
    sessionViewModel: SessionViewModel
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
                        logoutState.value = true  // Trigger the logout state // Clear the backstack
                        sessionViewModel.logout() // Call the logout function
                        navController.navigate("Login") {
                            popUpTo(0) { inclusive = true } // Clear the backstack
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

@Preview
@Composable
fun PreviewLogoutDialog() {
    // Preview the dialog
    val dialogState = androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(true) }
    val logoutState = androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
    val sessionViewModel = SessionViewModel(Application())
    val navController = rememberNavController()
    LogoutDialog(navController, dialogState, logoutState, "map", sessionViewModel = sessionViewModel)
}