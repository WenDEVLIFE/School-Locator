package com.example.schoollocator.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

// This is our data class
data class MenuItem(
    @DrawableRes val icon: Int,
    val label: String,
    val trailingIcon: ImageVector,
    val onClick: () -> Unit
)