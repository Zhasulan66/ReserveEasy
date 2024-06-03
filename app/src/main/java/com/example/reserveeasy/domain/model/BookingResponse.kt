package com.example.reserveeasy.domain.model

data class BookingResponse(
    val totalHits: Int,
    val bookings: List<Booking>
)

data class BookingIdResponse(
    val id: String
)
