package com.example.schoollocator.activity.maincomponent.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style

// This method is used to load  the maps
@Composable
fun Map(Modifier: Modifier, OnClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val mapView = MapView(context)
            mapView.getMapAsync(object : OnMapReadyCallback {
                override fun onMapReady(mapboxMap: MapboxMap) {
                    // Use MapLibre's predefined style URL
                    mapboxMap.setStyle("https://api.maptiler.com/maps/streets/style.json?key=2FeRdU4DmzOy7sPnsesD")
                }
            })
            mapView
        },
        modifier = Modifier.fillMaxSize()
    )
}