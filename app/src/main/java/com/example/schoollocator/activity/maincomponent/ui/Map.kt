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
    var userLocation by remember { mutableStateOf<Location?>(null) }
    var mapReady by remember { mutableStateOf(false) }
    var permissionGranted by remember { mutableStateOf(false) }

    // Initialize Mapbox
    remember { Mapbox.getInstance(context) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        permissionGranted = isGranted
        if (isGranted) {
            Log.d("MapDebug", "Location permission granted.")
            getUserLocation(context) { location ->
                userLocation = location
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
                getUserLocation(context) { location ->
                    userLocation = location
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
                        enableLocationComponent(mapboxMap, context)
                    }

                    userLocation?.let { location ->
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


// get the user location and enable location component
@SuppressLint("MissingPermission")
private fun enableLocationComponent(mapboxMap: MapboxMap, context: Context) {
    val locationComponent = mapboxMap.locationComponent

    val locationComponentOptions = LocationComponentOptions.builder(context)
        .build()

    val locationComponentActivationOptions = LocationComponentActivationOptions.builder(context, mapboxMap.style!!).apply {
        locationComponentOptions(locationComponentOptions)
    }.build()

    // activate location component
    locationComponent.activateLocationComponent(locationComponentActivationOptions)

    // enable location component
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        locationComponent.isLocationComponentEnabled = true
        locationComponent.cameraMode = CameraMode.TRACKING
        locationComponent.renderMode = RenderMode.COMPASS

        val lastLocation = locationComponent.lastKnownLocation

        // add a marker to the user's current location
        lastLocation?.let {
            val position = LatLng(it.latitude, it.longitude)
            mapboxMap.addMarker(
                com.mapbox.mapboxsdk.annotations.MarkerOptions()
                    .position(position)
                    .title("You are here")
            )

            // zoom the camera to the user's current location
            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 14.0))
        }
    }
}


// get user location
@SuppressLint("MissingPermission")
private fun getUserLocation(context: Context, onLocationReceived: (Location) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val locationTask: Task<Location> = fusedLocationClient.lastLocation

    // get last known location
    locationTask.addOnSuccessListener { location: Location? ->
        if (location != null) {
            Log.d("MapDebug", "Last known location: ${location.latitude}, ${location.longitude}")
            onLocationReceived(location)
        } else {
            Log.e("MapDebug", "Last known location is null, requesting a new location.")
            requestNewLocationData(context, onLocationReceived)
        }
    }.addOnFailureListener { e ->
        Log.e("MapDebug", "Error getting last known location: ${e.message}")
        Toast.makeText(context, "Error getting location", Toast.LENGTH_SHORT).show()
    }
}

// request new location data
@SuppressLint("MissingPermission")
private fun requestNewLocationData(context: Context, onLocationReceived: (Location) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    // create location request
    val locationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        interval = 10000
        fastestInterval = 5000
    }

    // create location callback
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult?.locations?.let { locations ->
                for (location in locations) {
                    Log.d("MapDebug", "New location received: ${location.latitude}, ${location.longitude}")
                    onLocationReceived(location)
                    fusedLocationClient.removeLocationUpdates(this)
                    break
                }
            } ?: Log.e("MapDebug", "Location result is null")
        }
    }

    fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
}

// add marker and zoom to user location
private fun addMarkerAndZoom(mapboxMap: MapboxMap, style: Style, context: Context, userLatLng: LatLng) {
    if (style.getSource("marker-source") == null) {
        val sourceId = "marker-source"
        val source = GeoJsonSource(
            sourceId,
            FeatureCollection.fromFeatures(
                arrayOf(Feature.fromGeometry(Point.fromLngLat(userLatLng.longitude, userLatLng.latitude)))
            )
        )

        // Add the source to the map
        style.addSource(source)
    }

    // Add the marker image to the map
    if (style.getLayer("marker-layer") == null) {
        val markerImageId = "custom-marker"
        val drawableResource = BitmapFactory.decodeResource(context.resources, R.drawable.baseline_location_on_24)
        style.addImage(markerImageId, drawableResource)

        // Add the marker layer to the map
        val layerId = "marker-layer"
        val symbolLayer = SymbolLayer(layerId, "marker-source").withProperties(
            PropertyFactory.iconImage(markerImageId),
            PropertyFactory.iconSize(1.0f)
        )
        style.addLayer(symbolLayer)
    }

    // This is for the zoom
    mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 14.0))
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
