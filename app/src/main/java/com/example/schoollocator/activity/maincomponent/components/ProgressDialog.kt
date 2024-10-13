package com.example.schoollocator.activity.maincomponent.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ProgressDialog(isLoading: Boolean) {
    if (isLoading) {
        Dialog(onDismissRequest = {}) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}