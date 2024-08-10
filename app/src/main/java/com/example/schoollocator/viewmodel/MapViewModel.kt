package com.example.schoollocator.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MapViewModel: ViewModel() {

    val querySearch   = mutableStateOf("")
    val searchQuery:State<String> = querySearch
}