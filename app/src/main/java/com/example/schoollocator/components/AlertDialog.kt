package com.example.schoollocator.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AlertDialog(
    title: String,
    message: String,
    dialogState: MutableState<Boolean>,
    onDismiss: () -> Unit = {}
) {
    if (dialogState.value) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = {
                dialogState.value = false
                onDismiss()
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = message)
            },
            confirmButton = {
                Button(
                    onClick = {
                        dialogState.value = false // Dismiss the dialog
                        onDismiss()
                    }
                ) {
                    Text("Ok")
                }
            }
        )
    }
}