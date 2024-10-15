package com.example.schoollocator.database

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.schoollocator.activity.maincomponent.components.AlertDialog
import com.example.schoollocator.viewmodel.SignUpModel

@Composable
fun InsertUserScreen(userData: Map<String, Any>) {
    val context = LocalContext.current
    val userViewModel: SignUpModel = viewModel()

    // Call insertUser function
    userViewModel.insertUser(userData)

    // Observe dialog state and show AlertDialog
    if (userViewModel.dialogState.value) {
        AlertDialog(
            title = "Registration", // Provide a title here
            message = userViewModel.dialogMessage.value,
            dialogState = userViewModel.dialogState,
        )
    }
}