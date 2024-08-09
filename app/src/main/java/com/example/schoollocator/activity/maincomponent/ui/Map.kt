package com.example.schoollocator.activity.maincomponent.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.schoollocator.BuildConfig
import com.mapbox.mapboxsdk.maps.MapView

// This method is used to load the maps
@Composable
fun Map(modifier: Modifier = Modifier,  OnClick: () -> Unit = {}) {
    AndroidView(
        factory = { context ->

            // Create MapView
            val mapView = MapView(context)
            mapView.onCreate(null)  // Required to set up the lifecycle

            // Set the Mapbox style
            mapView.getMapAsync { mapboxMap ->
                mapboxMap.setStyle(
                    "https://api.maptiler.com/maps/streets-v2/style.json?key=${BuildConfig.MAPTILER_API_KEY}"
                ) { style ->
                    // Style loaded successfully
                }
            }

            // Return the MapView
            mapView
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun MainMap(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    // State to hold search query
    var query by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // Map view
        Map(modifier = Modifier.fillMaxSize())

        // Search bar overlay
        Column(modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(16.dp)
            .fillMaxWidth()
        ) {
            SearchBar(
                query = query,
                onQueryChanged = { newQuery -> query = newQuery },
                onSearch = { /* Handle search action here */ }
            )
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    // Use TextField for the search bar
    TextField(
        value = query,
        onValueChange = { newValue ->
            onQueryChanged(newValue)
        },
        label = { Text("Search") },
        placeholder = { Text("Enter search term") },
        singleLine = true,
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch() }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview
@Composable
fun MapPreview() {
    MainMap()
}
