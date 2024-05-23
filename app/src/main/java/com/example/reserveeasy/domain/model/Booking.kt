package com.example.reserveeasy.domain.model

data class Booking(
    val id: String,
    val userId: String,
    val restaurantId: String,
    val reservedTime: String,
    val tableId: String,
    val createdAt: String,
    val updatedAt: String
)
