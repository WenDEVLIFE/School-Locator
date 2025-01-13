package com.example.schoollocator.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.Typography
import com.example.schoollocator.ui.theme.darkblue
import com.example.schoollocator.ui.theme.white900
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarScreenWithBackBtn(modifier: Modifier, tittle: String, onBackClick: () -> Unit) {
    val screenSize = getScreenSize()

    TopAppBar(
        title = {
            Text(
                text = tittle,
                style = Typography.bodySmall,
                fontSize = if (screenSize == ScreenSize.SMALL) 25.sp else 35.sp,
                color = white900
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Back",
                    tint = white900
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = darkblue
        )
    )
}