package com.example.schoollocator.Apikey

import android.app.Application
import com.mapbox.mapboxsdk.Mapbox

class MapAPI : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Mapbox here
        Mapbox.getInstance(this)
    }
}