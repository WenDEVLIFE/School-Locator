package com.example.schoollocator.activity.maincomponent.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import java.lang.reflect.Modifier

@Composable
fun ProgressDialog(isLoading: Boolean) {
    if (isLoading) {
        Dialog(onDismissRequest = {}) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}