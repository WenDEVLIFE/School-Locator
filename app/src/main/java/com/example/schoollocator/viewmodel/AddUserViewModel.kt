package com.example.schoollocator.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddUserViewModel:ViewModel() {
    // get the username and password
    private val _username = mutableStateOf("")
    val username: MutableState<String> = _username

    private val _password = mutableStateOf("")
    val password: MutableState<String> = _password

    private val _email = mutableStateOf("")
    val email: MutableState<String> = _email
    // This is for the password text field
    var _passwordVisible = mutableStateOf(false)
    val isPasswordVisible: MutableState<Boolean> = _passwordVisible

    var Success = mutableStateOf(false)
    val isSuccess: MutableState<Boolean> = Success

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