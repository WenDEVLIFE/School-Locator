package com.example.schoollocator.ENUM

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

enum class ScreenSize {
                      SMALL, MEDIUM, LARGE
}
@Composable
fun getScreenSize(): ScreenSize {
    val configuration = LocalConfiguration.current
    return when {
        configuration.screenWidthDp < 600 -> ScreenSize.SMALL
        configuration.screenWidthDp < 840 -> ScreenSize.MEDIUM
        else -> ScreenSize.LARGE
    }
}