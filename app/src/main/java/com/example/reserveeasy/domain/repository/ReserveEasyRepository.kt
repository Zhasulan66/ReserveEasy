package com.example.reserveeasy.domain.repository

import com.example.reserveeasy.domain.model.BookingIdResponse
import com.example.reserveeasy.domain.model.BookingRequest
import com.example.reserveeasy.domain.model.BookingResponse
import com.example.reserveeasy.domain.model.LoginResponse
import com.example.reserveeasy.domain.model.Restaurant
import com.example.reserveeasy.domain.model.RestaurantResponse
import com.example.reserveeasy.domain.model.RestaurantsResponse
import com.example.reserveeasy.domain.model.TableSchemeResponse
import com.example.reserveeasy.domain.model.User
import com.example.reserveeasy.domain.model.UserRequest

interface ReserveEasyRepository {

    suspend fun registerUser(userRequest: UserRequest): User

    suspend fun loginUser(userRequest: UserRequest): LoginResponse

    suspend fun getAllRestaurants(): RestaurantsResponse

    suspend fun getRestaurantById(id: String): RestaurantResponse

    suspend fun getAllBookings(): BookingResponse

    suspend fun getTableScheme(restaurantId: String): TableSchemeResponse

    suspend fun createBooking(bookingRequest: BookingRequest): BookingIdResponse
}