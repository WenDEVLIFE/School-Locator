package com.example.schoollocator.viewmodel

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val username: String,
    val email: String,
    val role: String
)