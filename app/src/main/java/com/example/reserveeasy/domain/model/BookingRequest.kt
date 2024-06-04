package com.example.reserveeasy.domain.model

data class BookingRequest(
    val userId: String,
    val restaurantId: String,
    val tableId: String,
    val reservedTime: String
)
