package com.example.schoollocator.activity.maincomponent.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.schoollocator.BuildConfig
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.WhiteCus
import com.example.schoollocator.viewmodel.MapViewModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource

@Composable
fun Map(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var mapReady by remember { mutableStateOf(false) }
    var permissionGranted by remember { mutableStateOf(false) }

    // our view model
    val mapModel: MapViewModel = viewModel()

    // Initialize Mapbox
    remember { Mapbox.getInstance(context) }

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

    LaunchedEffect(Unit) {
        when {
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                permissionGranted = true
                mapModel.getUserLocation(context) { location ->
                    mapModel.userLocation.value = location
                    mapReady = true
                }
            }
            else -> {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    val mapView = remember { MapView(context) }

    AndroidView(
        factory = {
            mapView.apply {
                onCreate(null)  // Required for lifecycle management
            }
        },
        modifier = modifier.fillMaxSize(),
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
                    } ?: run {
                        Log.d("MapDebug", "User location is null.")
                    }
                } ?: run {
                    Log.d("MapDebug", "Failed to load style")
                }
            }
        }
    )

    DisposableEffect(Unit) {
        onDispose {
            mapView.onStop()
            mapView.onPause()
            mapView.onLowMemory()
            mapView.onDestroy()
        }
    }

    LaunchedEffect(Unit) {
        mapView.onSaveInstanceState(Bundle())
    }
}



// MainMap composable
@Composable
fun MainMap(modifier: Modifier = Modifier) {
    val screenSize = getScreenSize()
    val mapModel: MapViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        Map(modifier = Modifier.fillMaxSize())

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
