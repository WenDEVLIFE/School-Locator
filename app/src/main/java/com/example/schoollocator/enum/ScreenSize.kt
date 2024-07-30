package com.example.schoollocator.enum

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

enum class ScreenSize {
                      SMALL, MEDIUM, LARGE
}
@Composable
fun getScreenSize(): ScreenSize {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    val smallestWidth = minOf(screenWidth, screenHeight)

    return when {
        smallestWidth < 600 -> ScreenSize.SMALL
        smallestWidth < 840 -> ScreenSize.MEDIUM
        else -> ScreenSize.LARGE
    }
}