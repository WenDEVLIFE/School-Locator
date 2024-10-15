package com.example.schoollocator.activity.Screens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.ui.theme.lightgreen
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.ui.theme.materialLightGreen
import com.example.schoollocator.viewmodel.SchoolViewModel

@Composable
fun SchoolScreen(modifier: Modifier = Modifier,
    navController: NavHostController
) {
      //  get the view model
       val viewModel:SchoolViewModel= viewModel()

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
                TopAppBarState(modifier = Modifier, tittle = "Schools")
                Spacer(modifier = Modifier.size(10.dp))

                // Search bar
                SearchBar(
                    viewModel.searchSchoolQuery.value,
                    onQueryChanged = { newQuery ->  viewModel.searchSchoolQuery.value = newQuery },
                    onSearch = { /* Handle search action here */ }
                )
                Spacer(modifier = Modifier.size(10.dp))

                // Lazy Column
                SchoolList(modifier = Modifier.weight(1f))
            }

            // Floating action button
            FloatingActionButton(
                onClick = {
                    // Handle FAB click
                    navController.navigate("AddSchool"){
                        launchSingleTop = true
                        restoreState = true
                    }

                },
                containerColor = materialLightGreen,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp)
                    .zIndex(10f)  // Ensures it is above other components
            ) {
                // Change the icon
                Icon(
                    imageVector = Icons.Default.Add, // Use a predefined icon
                    contentDescription = "Add School",
                    tint = materialGreen
                )
            }
        }
    }

@Composable
fun SchoolList(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ensure LazyColumn fills the remaining space
            .padding(11.dp)
            .background(lightgreen),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
    ) {
        items(20) {
            Text(text = "School $it")
        }

    }
}

@Preview
@Composable
fun SchoolScreenPreview() {
    SchoolScreen(modifier = Modifier, navController = rememberNavController())
}
