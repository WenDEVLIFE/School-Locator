package com.example.schoollocator.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun performSignUp() {
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
                setShowOTP(true)

            }
        }
    }

}