package com.example.schoollocator.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO 1: Added funtions that will verify the OTP from the email sended to the user
// TODO 2: Added functions that will set the OTP

class OTPViewModel: ViewModel() {

    val otp: MutableState<String> = mutableStateOf("")

    // time and state
    private val _time = mutableIntStateOf(60)
    val time: MutableState<Int> = _time

    // success and state
    private var _showSuccess = mutableStateOf(false)
    val showSuccess: MutableState<Boolean> = _showSuccess


    // This will go back to the previous screen
    private var backPressed3 = mutableStateOf(false)
    val isBackPressed3: MutableState<Boolean> = backPressed3

     //This will start the timer once it is called
    fun startTimer() {
        _time.intValue = 60 // Reset the timer to 60
        viewModelScope.launch {
            while (_time.intValue > 0) {
                delay(1000L)
                _time.intValue -= 1
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

    fun performOTP() {
        // This will go back to the previous screen
        viewModelScope.launch {
            // Simulate a long-running task (like network request)
            withContext(Dispatchers.IO) {
                // Perform your network or database operation here
                // Example: networkRequest()
                delay(1000) // Simulating delay for demonstration
            }

            // Update the UI on the main thread
            withContext(Dispatchers.Main) {
                // Update the showOTP state or other UI state
                //startTimer()
            }
        }
    }

}

