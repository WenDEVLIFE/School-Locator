package com.example.schoollocator.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

}