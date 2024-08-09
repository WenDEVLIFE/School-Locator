package com.example.schoollocator.activity.maincomponent.ui

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.schoollocator.BuildConfig
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style

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
                    "https://api.maptiler.com/maps/streets-v2/style.json?key=${BuildConfig.MAPTILER_API_KEY}",
                    object : Style.OnStyleLoaded {
                        override fun onStyleLoaded(style: Style) {
                            // Style loaded successfully
                        }
                    }
                )
            }

            // Return the MapView
            mapView
        },
        modifier = modifier.fillMaxSize()
    )
}

@Preview
@Composable
fun MapPreview() {
    Map()
}
