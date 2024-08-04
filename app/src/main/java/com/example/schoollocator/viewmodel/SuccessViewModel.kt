package com.example.schoollocator.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SuccessViewModel: ViewModel() {

    // This will go back to the previous screen
    private var backPressed1 = mutableStateOf(false)
    val isBackPressed1: State<Boolean> = backPressed1
    private var backPressed2 = mutableStateOf(false)
    val isBackPressed2: State<Boolean> = backPressed2

    // This will set the back pressed 1
    fun setBackPressed1(value: Boolean) {
        backPressed1.value = value
    }

    // This will set the back pressed 2
    fun setBackPressed2(value: Boolean) {
        backPressed2.value = value
    }
}