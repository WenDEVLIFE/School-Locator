package com.example.schoollocator.activity.maincomponent.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoollocator.ui.theme.lightgreen

@Composable

// TODO: Implement the UserScreen composable function
fun UserScreen() {

    // This is the state of the search query
    val query = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
        .background(lightgreen)
    ) {
        SearchBar(
            query = query.value,
            onQueryChanged = { newQuery ->query.value = newQuery },
            onSearch = { /* Handle search action here */ }
        )
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

@Preview
@Composable
fun PreviewUserScreen() {
    UserScreen()
}