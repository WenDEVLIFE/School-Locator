package com.example.schoollocator.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpModel : ViewModel() {
    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _showOTP = mutableStateOf(false)
    val showOTP: State<Boolean> = _showOTP

    private val _time = mutableStateOf(60)
    val time: State<Int> = _time

    private val _showSuccess = mutableStateOf(false)
    val showSuccess: State<Boolean> = _showSuccess

    fun setUsername(value: String) {
        _username.value = value
    }

    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun setShowOTP(value: Boolean) {
        _showOTP.value = value
    }

    fun startTimer() {
        viewModelScope.launch {
            while (_time.value > 0) {
                delay(1000L)
                _time.value -= 1
            }
        }
    }

    fun setShowSuccess(value: Boolean) {
        _showSuccess.value = value
    }
}