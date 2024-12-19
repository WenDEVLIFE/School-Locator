package com.example.schoollocator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.schoollocator.components.SessionManager

class SessionViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionManager = SessionManager(application)

    private val _isLoggedIn = MutableLiveData(sessionManager.isLoggedIn)
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    private val _username = MutableLiveData(sessionManager.username)
    val username: LiveData<String?> get() = _username

    private val _email = MutableLiveData(sessionManager.email)
    val email: LiveData<String?> get() = _email

    private val _role = MutableLiveData(sessionManager.role)
    val role: LiveData<String?> get() = _role

    fun login(username: String, email: String, role: String) {
        sessionManager.isLoggedIn = true
        sessionManager.username = username
        sessionManager.email = email
        sessionManager.role = role
        _isLoggedIn.value = true
        _username.value = username
        _email.value = email
        _role.value = role
    }

    var usernames: String?
        get() = sessionManager.username
        set(value) {
            sessionManager.username = value
            _username.value = value
        }

    var emails: String?
        get() = sessionManager.email
        set(value) {
            sessionManager.email = value
            _email.value = value
        }

    var roles: String?
        get() = sessionManager.role
        set(value) {
            sessionManager.role = value
            _role.value = value
        }



    fun logout() {
        sessionManager.clearSession()
        _isLoggedIn.value = false
        _username.value = null
        _email.value = null
        _role.value = null
    }
    fun isLoggedIn(): Boolean {
        return sessionManager.isLoggedIn
    }
}