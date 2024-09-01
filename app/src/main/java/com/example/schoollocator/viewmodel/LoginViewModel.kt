package com.example.schoollocator.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


// TODO 1: Added funtions that will verify the username and password from the firebase
// TODO 2: Added functions that will set the username and password
// TODO 3: Added functionns that will pass the username and password to the screen

class LoginViewModel : ViewModel() {

    // get the username and password
    private val _username = mutableStateOf("")
    val username: MutableState<String> = _username

    private val _password = mutableStateOf("")
    val password: MutableState<String> = _password


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