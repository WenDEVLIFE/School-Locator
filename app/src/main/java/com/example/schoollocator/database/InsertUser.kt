package com.example.schoollocator.database

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.schoollocator.components.AlertDialog
import com.example.schoollocator.viewmodel.SignUpModel

@Composable
fun InsertUserScreen(userData: Map<String, String?>) {
    val context = LocalContext.current
    val userViewModel: SignUpModel = viewModel()

    // Call insertUser function only once
    LaunchedEffect(Unit) {
        userViewModel.insertUser(userData, context)
    }

}