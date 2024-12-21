package com.example.schoollocator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenuViewModel: ViewModel() {

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl: StateFlow<String?> get() = _imageUrl

    fun fetchImageUrl() {
        viewModelScope.launch {

            val db = FirebaseStorage.getInstance()

           // db.co
        }
    }
}