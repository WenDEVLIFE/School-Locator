package com.example.schoollocator.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OTPViewModel: ViewModel() {

    // time and state
    private val _time = mutableStateOf(60)
    val time: State<Int> = _time

    // success and state
    private var _showSuccess = mutableStateOf(false)
    val showSuccess: State<Boolean> = _showSuccess


    // This will go back to the previous screen
    private var backPressed3 = mutableStateOf(false)
    val isBackPressed3: State<Boolean> = backPressed3

    // This will start the timer once it is called
    fun startTimer() {
        _time.value = 60 // Reset the timer to 60
        viewModelScope.launch {
            while (_time.value > 0) {
                delay(1000L)
                _time.value -= 1
            }
        }
    }

    // This will set the time to 60
    fun setShowSuccess(value: Boolean) {
        _showSuccess.value = value
    }


    // This will set the back pressed 3
    fun setBackPressed3(value: Boolean) {
        backPressed3.value = value
    }

}
