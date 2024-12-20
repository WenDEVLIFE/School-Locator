package com.example.schoollocator.components

object VerifyData {

    fun checkUsername(username: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9_]{6,12}$")
        return regex.matches(username)
    }

    fun checkUsernameHasNoNumber(username: String): Boolean {
        val regex = Regex("^[a-zA-Z_]{6,12}$")
        return regex.matches(username)
    }
    fun checkUsernameHasNoSpecialChar(username: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9_]+$")
        return regex.matches(username)
    }

    fun checkPassword(password: String): Boolean {
        val regex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{8,12}$")
        return regex.matches(password)
    }

    fun isPasswordhasUpperCase(password: String): Boolean {
        val regex = Regex("^(?=.*[A-Z]).+$")
        return regex.matches(password)
    }

    fun isPasswordhasSpecialChar(password: String): Boolean {
        val regex = Regex("^(?=.*[@#\$%^&+=]).+$")
        return regex.matches(password)
    }

    fun checkEmail(email: String): Boolean {
        val regex = Regex("^[A-Za-z0-9+_.-]+@(.+)$")
        return regex.matches(email)
    }



}