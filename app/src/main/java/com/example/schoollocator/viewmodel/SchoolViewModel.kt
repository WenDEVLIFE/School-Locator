package com.example.schoollocator.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SchoolViewModel:ViewModel() {

    private val schoolQuery = mutableStateOf("")  // School query
    val searchSchoolQuery: MutableState<String> = schoolQuery


    // TODO: Implement the LoadSchool function
    fun LoadSchool(){

    }

    // TODO Implement the fun onQueryChange function
    fun onQueryChange(query:String){
        schoolQuery.value = query
    }
}