package com.example.schoollocator.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FavoriteViewModel: ViewModel() {

    private val favoriteQuery = mutableStateOf("")  // Favorite Query Search
    val seearchFavorites: MutableState<String> = favoriteQuery

    // TODO Implement the fun onQueryChange function
    fun onQueryChange(query: String) {
        seearchFavorites.value = query
    }
}
