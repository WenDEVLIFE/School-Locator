package com.example.schoollocator.activity.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.components.SearchBar
import com.example.schoollocator.components.TopAppBarScreen
import com.example.schoollocator.ui.theme.lightgreen
import com.example.schoollocator.viewmodel.MessageViewModel

@Composable
fun ChatScreen(modifier: Modifier = Modifier,
               navController: NavHostController
) {
    //  get the view model
    val viewModel: MessageViewModel = viewModel()

    // Go back to home screen
    BackHandler {
        navController.navigate("Home") {
            launchSingleTop = true
            restoreState = true
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(lightgreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(lightgreen)
        ) {
            // Top bar
            TopAppBarScreen(modifier = Modifier, tittle = "Messages")
            Spacer(modifier = Modifier.size(10.dp))

            // Search bar
            SearchBar(
                viewModel.chatQueryState.value,
                onQueryChanged = { newQuery ->  viewModel.chatQueryState.value = newQuery },
                onSearch = { /* Handle search action here */ }
            )
            Spacer(modifier = Modifier.size(10.dp))

            // Lazy Column
            MessageList(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun MessageList(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ensure LazyColumn fills the remaining space
            .padding(11.dp)
            .background(lightgreen),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
    ) {
        items(20) {
            Text(text = "Chat$it")
        }

    }
}

@Preview
@Composable
fun PreviewMessageScreen() {
    ChatScreen(modifier = Modifier, navController = rememberNavController())
}