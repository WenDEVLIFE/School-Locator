package com.example.schoollocator.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import at.favre.lib.crypto.bcrypt.BCrypt
import com.google.firebase.firestore.FirebaseFirestore

// TODO 1: Added functionns that will pass the OTP to the screen
// TODO 2: Added function that will perform the sign up
// TODO 3: Added function that will pass the value of username, email, and password to the screen
class SignUpModel : ViewModel() {

    // username and state
    private val _username = mutableStateOf("")
    val username: MutableState<String> = _username

    // email and state
    private val _email = mutableStateOf("")
    val email: MutableState<String> = _email

    // password and state
    private val _password = mutableStateOf("")
    val password: MutableState<String> = _password

    // password visible and state
    private var _passwordVisible = mutableStateOf(false)
    val isPasswordVisible: MutableState<Boolean> = _passwordVisible

    // OTP and state
    private val _showOTP = mutableStateOf(false)
    val showOTP: MutableState<Boolean> = _showOTP

    val dialogState = mutableStateOf(false)
    val dialogMessage = mutableStateOf("")

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


    // Insert the data from the user
    fun insertUser(userData: Map<String, String?>) {
        val db = FirebaseFirestore.getInstance()

        try {
            db.collection("Users").whereEqualTo("Username", userData["username"]).get().addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    val password = hashPassword(userData["password"].toString())
                    val username = userData["username"].toString()
                    val email = userData["email"].toString()
                    val addUser = hashMapOf(
                        "Username" to username,
                        "Password" to password,
                        "Email" to email,
                        "Role" to "User"
                    )

                    db.collection("Users").add(addUser).addOnSuccessListener {
                        dialogMessage.value = "User added successfully"
                        dialogState.value = true
                    }.addOnFailureListener {
                        dialogMessage.value = "Failed to add user"
                        dialogState.value = true
                    }
                } else {
                    dialogMessage.value = "User already exists"
                    dialogState.value = true
                }
            }
        } catch (e: Exception) {
            println("Error: $e")
        }
    }

    fun hashPassword(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray())
    }

    fun verifyPassword(password: String, hashedPassword: String): Boolean {
        val result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword)
        return result.verified
    }

}