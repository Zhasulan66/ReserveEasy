package com.example.reserveeasy.data.repository

import com.example.reserveeasy.data.remote.ReserveEasyApiService
import com.example.reserveeasy.domain.model.LoginResponse
import com.example.reserveeasy.domain.model.Restaurant
import com.example.reserveeasy.domain.model.RestaurantResponse
import com.example.reserveeasy.domain.model.RestaurantsResponse
import com.example.reserveeasy.domain.model.User
import com.example.reserveeasy.domain.model.UserRequest
import com.example.reserveeasy.domain.repository.ReserveEasyRepository

class ReserveEasyRepositoryImpl(
    private val api: ReserveEasyApiService
) : ReserveEasyRepository {

    override suspend fun registerUser(userRequest: UserRequest): User {
        return api.registerUser(userRequest)
    }

    override suspend fun loginUser(userRequest: UserRequest): LoginResponse {
        return api.loginUser(userRequest)
    }

    override suspend fun getAllRestaurants(): RestaurantsResponse {
        return api.getAllRestaurants()
    }

    override suspend fun getRestaurantById(id: String): RestaurantResponse {
        return api.getRestaurantById(id)
    }
}