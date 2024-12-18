package com.example.schoollocator.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import at.favre.lib.crypto.bcrypt.BCrypt
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch


class LoginViewModel(private val sessionViewModel: SessionViewModel) : ViewModel() {

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

    fun LoginDB(navController: NavHostController) {

        val db = Firebase.firestore

        db.collection("Users").whereEqualTo("Username", _username.value).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Log.d(TAG, "No such document")
                } else {
                    for (document in documents) {
                        val password = document.data["Password"].toString()
                        val role  = document.data["Role"].toString()
                        val email = document.data["Email"].toString()


                        if (verifyPassword(_password.value, password)) {
                            viewModelScope.launch {
                                sessionViewModel.login(_username.value, email, role)
                            }

                            navController.navigate("Map"){
                                popUpTo("Login") {
                                    inclusive = true
                                }
                            }
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }
                }

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

    fun verifyPassword(password: String, hashedPassword: String): Boolean {
        val result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword)
        return result.verified
    }






}