package com.example.reserveeasy.data.local

import com.example.reserveeasy.domain.model.Booking

object LocalBookingDataProvider {
    val defaultBooking = getBookingData()[0]

    fun getBookingData(): List<Booking> {
        return listOf(
            Booking(
                id = "1",
                userId = "1",
                restaurantId = "1",
                reservedTime = "2024-05-23T18:00:00.000Z",
                tableId = "1",
                createdAt = "2024-05-23T07:09:02.852Z",
                updatedAt = "2024-05-23T07:09:02.852Z"
            ),
            Booking(
                id = "2",
                userId = "2",
                restaurantId = "2",
                reservedTime = "2024-05-25T14:30:00.000Z",
                tableId = "1",
                createdAt = "2024-05-23T07:09:02.852Z",
                updatedAt = "2024-05-23T07:09:02.852Z"
            ),
            Booking(
                id = "3",
                userId = "3",
                restaurantId = "3",
                reservedTime = "2024-05-29T11:00:00.000Z",
                tableId = "1",
                createdAt = "2024-05-23T07:09:02.852Z",
                updatedAt = "2024-05-23T07:09:02.852Z"
            ),
        )
    }
}