package com.example.schoollocator.activity.maincomponent.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.schoollocator.BuildConfig
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style

// This method is used to load  the maps
@Composable
fun Map(Modifier: Modifier, OnClick: () -> Unit) {

    AndroidView(

        factory = { context ->
            // No need to initialize Mapbox here as it's done in the Application or Activity
            val mapView = MapView(context)
            mapView.getMapAsync { mapboxMap ->
                mapboxMap.setStyle("https://demotiles.maplibre.org/style.json")
            }
            mapView
        },
        modifier = Modifier.fillMaxSize()
    )
}