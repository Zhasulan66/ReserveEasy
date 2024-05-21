package com.example.reserveeasy.domain.model

data class User(
    val id: String,
    val email: String,
    val password: String,
    val isStaff: Boolean,
    val isSuperAdmin: String,
    val createdAt: String,
    val updatedAt: String
)
