package com.example.reserveeasy.domain.repository

import com.example.reserveeasy.domain.model.LoginResponse
import com.example.reserveeasy.domain.model.Restaurant
import com.example.reserveeasy.domain.model.RestaurantResponse
import com.example.reserveeasy.domain.model.RestaurantsResponse
import com.example.reserveeasy.domain.model.User
import com.example.reserveeasy.domain.model.UserRequest

interface ReserveEasyRepository {

    suspend fun registerUser(userRequest: UserRequest): User

    suspend fun loginUser(userRequest: UserRequest): LoginResponse

    suspend fun getAllRestaurants(): RestaurantsResponse

    suspend fun getRestaurantById(id: String): RestaurantResponse
}