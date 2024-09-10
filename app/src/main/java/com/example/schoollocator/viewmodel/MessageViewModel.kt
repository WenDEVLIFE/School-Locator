package com.example.schoollocator.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MessageViewModel:ViewModel() {
    //TODO : Add the view model logic here
    private val chatQuery = mutableStateOf("")
    val chatQueryState :MutableState<String> = chatQuery

    fun LoadChat(){
        //TODO : Add the logic to load chat here
    }
}