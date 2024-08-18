package com.example.schoollocator.activity.maincomponent.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schoollocator.ui.theme.Typography
import com.example.schoollocator.ui.theme.lightgreen
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.ui.theme.materialLightGreen
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize

@Composable

// TODO: Implement the UserScreen composable function
fun UserScreen() {

    // This is the state of the search query
    val query = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
        .background(lightgreen)
    ) {
        TopAppBarState(modifier = Modifier)
        Spacer(modifier = Modifier.size(10.dp))
        SearchBar(
            query = query.value,
            onQueryChanged = { newQuery ->query.value = newQuery },
            onSearch = { /* Handle search action here */ }
        )
        Spacer(modifier = Modifier.size(10.dp))
        UserList(modifier = Modifier.weight(1f))
    }


}

@Composable
fun UserList(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ensure LazyColumn fills the remaining space
            .padding(11.dp)
            .background(lightgreen),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
    ) {
        items(100) {
            Text(text = "Item $it")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarState(modifier: Modifier) {
    val screenSize = getScreenSize()

    TopAppBar(
        title = { Text(text = "User", style = Typography.bodySmall, fontSize = if (screenSize == ScreenSize.SMALL) 25.sp else 35.sp, color = materialGreen) },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = materialLightGreen
        )
    )
}

@Preview
@Composable
fun PreviewUserScreen() {
    UserScreen()
}