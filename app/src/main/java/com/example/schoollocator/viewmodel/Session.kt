package com.example.schoollocator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.schoollocator.components.SessionManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

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

        updateLiveData()
    }

    fun logout() {
        sessionManager.clearSession()
        updateLiveData()
    }

    fun checkLoginStatus(): Boolean {
        return sessionManager.isLoggedIn
    }

    private fun updateLiveData() {
        _isLoggedIn.value = sessionManager.isLoggedIn
        _username.value = sessionManager.username
        _email.value = sessionManager.email
        _role.value = sessionManager.role
    }

}
