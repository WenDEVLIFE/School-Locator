package com.example.schoollocator.activity.maincomponent.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AlertDialog(message:String, dialogState: MutableState<Boolean>) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
            dialogState.value = false
        },
        title = {
            Text(text = "Logout")
        },

        text = {
            Text(text = message)
        },

        confirmButton = {
            Button(
                onClick = {
                    // Clear the token
                    // Navigate to login
                    dialogState.value = false // Dismiss the dialog
                }
            ) {
                Text("Ok")
            }
        },

    )
}