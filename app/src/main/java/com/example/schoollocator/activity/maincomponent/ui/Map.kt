package com.example.schoollocator.activity.maincomponent.ui

import android.content.Context
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.schoollocator.BuildConfig
import com.example.schoollocator.ui.theme.WhiteCus
import com.example.schoollocator.viewmodel.MapViewModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.mapbox.mapboxsdk.maps.Style


@Composable
fun Map(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val context = LocalContext.current
    var userLocation by remember { mutableStateOf<Location?>(null) }

    RequestLocationPermission {
        getUserLocation(context) { location ->
            userLocation = location
        }
    }

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
                    userLocation?.let { location ->
                        val userLatLng = LatLng(location.latitude, location.longitude)
                        addMarkerAndZoom(mapboxMap, style, context, userLatLng)
                    }
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

    // Get the screen size
    val screenSize = getScreenSize()

    // State to hold search query
    val mapModel:MapViewModel  = viewModel ()

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

    // Get the screen size
    val screenSize = getScreenSize()

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

        textStyle = TextStyle(
            color = Color.Black,
            fontSize = if (screenSize == ScreenSize.SMALL) 12.sp else 15.sp
        ),

        colors = TextFieldDefaults.textFieldColors(
            containerColor = WhiteCus, // Set the background color here
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    )
}

private fun addMarkerAndZoom(mapboxMap: MapboxMap, style: Style, context: Context, userLatLng: LatLng) {
    // Create a SymbolManager
    val symbolManager = SymbolManager(mapView, mapboxMap, style)

    // Add a marker at the user's location with a custom icon
    symbolManager.create(
        SymbolOptions()
            .withLatLng(userLatLng)
            .withIconImage("custom-marker-icon-id") // Ensure you have added the custom marker icon to the style
            .withIconSize(1.0f)
    )

    // Zoom in on the user's location
    mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 14.0))
}

@Composable
fun RequestLocationPermission(onPermissionGranted: () -> Unit) {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            // Handle permission denial
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        when {
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                onPermissionGranted()
            }
            else -> {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }
}

@SuppressLint("MissingPermission")
fun getUserLocation(context: Context, onLocationReceived: (Location) -> Unit) {
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    val locationTask: Task<Location> = fusedLocationClient.lastLocation
    locationTask.addOnSuccessListener { location: Location? ->
        location?.let {
            onLocationReceived(it)
        }
    }
}

@Preview
@Composable
fun MapPreview() {
    MainMap()
}
