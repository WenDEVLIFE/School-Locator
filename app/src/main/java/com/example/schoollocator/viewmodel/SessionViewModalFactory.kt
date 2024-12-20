package com.example.schoollocator.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SessionViewModelFactoryN(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SessionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SessionViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}