package com.example.schoollocator.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpModel : ViewModel() {

    // username and state
    private val _username = mutableStateOf("")
    val username: State<String> = _username

    // email and state
    private val _email = mutableStateOf("")
    val email: State<String> = _email

    // password and state
    private val _password = mutableStateOf("")
    val password: State<String> = _password

    // password visible and state
    private var _passwordVisible = mutableStateOf(false)
    val isPasswordVisible: State<Boolean> = _passwordVisible

    // OTP and state
    private val _showOTP = mutableStateOf(false)
    val showOTP: State<Boolean> = _showOTP

    // time and state
    private val _time = mutableStateOf(60)
    val time: State<Int> = _time

    // success and state
    private var _showSuccess = mutableStateOf(false)
    val showSuccess: State<Boolean> = _showSuccess

    // This will go back to the previous screen
    var backPressed3 = mutableStateOf(false)
    val isBackPressed3: State<Boolean> = backPressed3


    // This will go back to the previous screen
    private var backPressed1 = mutableStateOf(false)
    val isBackPressed1: State<Boolean> = backPressed1
    private var backPressed2 = mutableStateOf(false)
    val isBackPressed2: State<Boolean> = backPressed2

    // This is for the username
    fun setUsername(value: String) {
        _username.value = value
    }

    // This is for the email
    fun setEmail(value: String) {
        _email.value = value
    }

    // This is for the password
    fun setPassword(value: String) {
        _password.value = value
    }

    // This is for the set OTP
    fun setShowOTP(value: Boolean) {
        _showOTP.value = value
    }

    // get the toogle password visible
    fun togglePasswordVisible() {
        _passwordVisible.value = !_passwordVisible.value
    }

    // This will set the back pressed 1
    fun setBackPressed1(value: Boolean) {
        backPressed1.value = value
    }

    // This will set the back pressed 2
    fun setBackPressed2(value: Boolean) {
        backPressed2.value = value
    }

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