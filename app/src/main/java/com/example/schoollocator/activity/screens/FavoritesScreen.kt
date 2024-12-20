package com.example.schoollocator.activity.screens

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.components.BottomNavigationBar
import com.example.schoollocator.components.LogoutDialog
import com.example.schoollocator.components.SearchBar
import com.example.schoollocator.components.TopAppBarScreen
import com.example.schoollocator.ui.theme.lightgreen
import com.example.schoollocator.viewmodel.FavoriteViewModel
import com.example.schoollocator.viewmodel.SessionViewModel

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier,
                    navController: NavHostController,
                    sessionViewModel: SessionViewModel
) {
    //  get the view model
    val viewModel: FavoriteViewModel = viewModel()
    val dialogState = remember { mutableStateOf(false) } // Initialize dialog state
    val logoutState = remember { mutableStateOf(false) } // Initialize logout state
    // Go back to home screen
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController,
                dialogState = dialogState,
                sessionViewModel= sessionViewModel)
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
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
                    TopAppBarScreen(modifier = Modifier, tittle = "Favorites")
                    Spacer(modifier = Modifier.size(10.dp))

                    // Search bar
                    SearchBar(
                        viewModel.seearchFavorites.value,
                        onQueryChanged = { newQuery ->  viewModel.seearchFavorites.value = newQuery },
                        onSearch = { /* Handle search action here */ }
                    )
                    Spacer(modifier = Modifier.size(10.dp))

                    // Lazy Column
                    FavoriteList(modifier = Modifier.weight(1f))
                }
            }
        }
    }

    // This is for the dialog state to show the dialog
    if (dialogState.value) {
        LogoutDialog(
            navController = navController,
            dialogState = dialogState,
            logoutState = logoutState,
            route = "Favorites",
            sessionViewModel = sessionViewModel
        )
    }

    // This is for the logout state
    if (logoutState.value) {
        logoutState.value = false // Reset the logout state
    }

}

@Composable
fun FavoriteList(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ensure LazyColumn fills the remaining space
            .padding(11.dp)
            .background(lightgreen),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
    ) {
        items(20) {
            Text(text = "Favorites $it")
        }

    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    val context = LocalContext.current
    val sessionViewModel = SessionViewModel(context.applicationContext as Application)
    FavoritesScreen(modifier = Modifier, navController = rememberNavController(), sessionViewModel = sessionViewModel)
}