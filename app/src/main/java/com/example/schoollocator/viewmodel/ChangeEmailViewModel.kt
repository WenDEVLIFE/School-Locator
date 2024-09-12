package com.example.schoollocator.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ChangeEmailViewModel: ViewModel() {

    private val  oldemail = mutableStateOf("")
    val Oldemail :MutableState<String> = oldemail
    private val  newemail = mutableStateOf("")
    val Newemail :MutableState<String> = newemail
    private val  password = mutableStateOf("")
    val Password :MutableState<String> = password
    private val passwordvisibility = mutableStateOf(false)
    val Passwordvisibility :MutableState<Boolean> = passwordvisibility


    // function to handle the visibility of the password
    fun onPasswordVisibilityClick() {
        passwordvisibility.value = !passwordvisibility.value
    }

     // TODO: Implement the function to handle the change of email
    fun RequestOnEmailChange(){
        // code to change email
    }

}