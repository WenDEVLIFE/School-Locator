package com.example.schoollocator.activity.maincomponent.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.schoollocator.BuildConfig
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.WhiteCus
import com.example.schoollocator.viewmodel.MapViewModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap

@Composable
fun Map(
    modifier: Modifier = Modifier,
    onMapReady: (MapboxMap) -> Unit
) {
    val context = LocalContext.current
    var mapReady by remember { mutableStateOf(false) }
    var permissionGranted by remember { mutableStateOf(false) }

    // ViewModel
    val mapModel: MapViewModel = viewModel()

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        permissionGranted = isGranted
        if (isGranted) {
            Log.d("MapDebug", "Location permission granted.")
            mapModel.getUserLocation(context) { location ->
                mapModel.userLocation.value = location
                mapReady = true
            }
        } else {
            Log.d("MapDebug", "Location permission denied.")
            Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    // Check for location permission
    LaunchedEffect(Unit) {
        when {

            // Check if permission is granted
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                permissionGranted = true
                mapModel.getUserLocation(context) { location ->
                    mapModel.userLocation.value = location
                    mapReady = true
                }
            }

            // Request permission if not granted
            else -> {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    // MapView component
    val mapView = remember { MapView(context) }

    // AndroidView to display the MapView
    AndroidView(
        factory = {
            mapView.apply {
                onCreate(null)  // Required for lifecycle management
            }
        },
        modifier = modifier.fillMaxSize(),

        // Update the MapView
        update = { mapView ->
            mapView.getMapAsync { mapboxMap ->
                Log.d("MapDebug", "MapboxMap is initialized")

                mapboxMap.setStyle(
                    "https://api.maptiler.com/maps/streets-v2/style.json?key=${BuildConfig.MAPTILER_API_KEY}"
                ) { style ->
                    Log.d("MapDebug", "Style loaded successfully.")

                    if (permissionGranted) {
                        mapModel.enableLocationComponent(mapboxMap, context)
                    }

                    mapModel.userLocation.value?.let { location ->
                        val userLatLng = LatLng(location.latitude, location.longitude)
                        // Add a marker or update map here if needed
                        Log.d("MapDebug", "User location: $userLatLng")
                    } ?: run {
                        Log.d("MapDebug", "User location is null.")
                    }
                } ?: run {
                    Log.d("MapDebug", "Failed to load style")
                }

                onMapReady(mapboxMap)
            }
        }
    )

    // Lifecycle management
    DisposableEffect(Unit) {
        onDispose {
            mapView.onStop()
            mapView.onPause()
            mapView.onLowMemory()
            mapView.onDestroy()
        }
    }

    // Save instance state
    LaunchedEffect(Unit) {
        mapView.onSaveInstanceState(Bundle())
    }
}
@Composable
fun MainMap(modifier: Modifier = Modifier) {
    val mapModel: MapViewModel = viewModel()
    val context = LocalContext.current
    var mapboxMap by remember { mutableStateOf<MapboxMap?>(null) }
    Box(modifier = modifier) {
        // Map component should be below everything, including the FAB and SearchBar
        Map(
            modifier = Modifier.fillMaxSize(),
            onMapReady = { map ->
                mapboxMap = map
            }
        )

        // Floating action button
        FloatingActionButton(
            onClick = {
                mapboxMap?.let { map ->
                    mapModel.enableLocationComponent(map,context)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .zIndex(10f)  // Ensures it is above other components
        ) {

            // change the icon
            Icon(
                painter = painterResource(id = R.drawable.baseline_gps_fixed_24),
                contentDescription = "Add School",
                tint = Color.White
            )
        }

        // Search bar
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            SearchBar(
                query = mapModel.searchQuery.value,
                onQueryChanged = { newQuery -> mapModel.querySearch.value = newQuery },
                onSearch = { /* Handle search action here */ }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    val screenSize = getScreenSize()

    // Search bar
    TextField(
        value = query,
        onValueChange = { newValue -> onQueryChanged(newValue) },
        label = { Text("Search") },
        placeholder = { Text("Enter search term") },
        singleLine = true,
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        textStyle = TextStyle(color = Color.Black, fontSize = if (screenSize == ScreenSize.SMALL) 12.sp else 15.sp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = WhiteCus,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )

}

@Preview
@Composable
fun MapPreview() {
    MainMap()
}