package com.example.schoollocator.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    // get the username and password
    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _password = mutableStateOf("")
    val password: State<String> = _password


    // This is for the password text field
    var _passwordVisible = mutableStateOf(false)
    val isPasswordVisible: State<Boolean> = _passwordVisible

    // This is for the password visibility
    fun passwordToogle() {
        _passwordVisible.value = !_passwordVisible.value
    }

    // This is for the username
    fun setUsername(value: String) {
        _username.value = value
    }

    // This is for the password
    fun setPassword(value: String) {
        _password.value = value
    }






}