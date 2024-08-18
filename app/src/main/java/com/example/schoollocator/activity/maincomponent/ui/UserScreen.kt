package com.example.schoollocator.activity.maincomponent.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable

// TODO: Implement the UserScreen composable function
fun UserScreen() {

    // This is the state of the search query
    val query = remember { mutableStateOf("") }

    SearchBar(
        query = query.value,
        onQueryChanged = { newQuery ->query.value = newQuery },
        onSearch = { /* Handle search action here */ }
    )
    LazyColumn {

    }

}

@Preview
@Composable
fun PreviewUserScreen() {
    UserScreen()
}