package com.example.schoollocator.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CreatedSchoolViewModel: ViewModel() {

    private val  _searchSchoolQuery = mutableStateOf("")
    val searchSchoolQuery = _searchSchoolQuery



    // TODO : Add logic to display the school
    fun DisplaySchool(){
        // Display the school add  logic here
    }

    // TODO : Add logic to search the school
    fun setSearchSchoolQuery(query: String){
        _searchSchoolQuery.value = query
    }
}