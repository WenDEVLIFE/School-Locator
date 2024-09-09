package com.example.schoollocator.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ChangePasswordViewModel: ViewModel() {

    // password visible and state
    private var _passwordVisible = mutableStateOf(false)
    val isPasswordVisible: MutableState<Boolean> = _passwordVisible

    private val _oldPassword = mutableStateOf("")
    val oldPassword: MutableState<String> = _oldPassword

    private val _newPassword = mutableStateOf("")
    val newPassword: MutableState<String> = _newPassword


    /*TODO : Added the following functions
    *   Password change functions
    *   onOldPasswordChange and onNewPasswordChange
    *
    *
    *  */
    fun onOldPasswordChange(oldPassword: String){
        _oldPassword.value = oldPassword
    }

    fun onNewPasswordChange(newPassword: String){
        _newPassword.value = newPassword
    }

    fun ChangeFunction(){
        //TODO : Add the password change function here
    }

    fun onPasswordVisibilityChange(){
        _passwordVisible.value = !_passwordVisible.value
    }


}