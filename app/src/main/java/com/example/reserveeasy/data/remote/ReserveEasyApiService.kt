package com.example.reserveeasy.data.remote

import com.example.reserveeasy.domain.model.LoginResponse
import com.example.reserveeasy.domain.model.Restaurant
import com.example.reserveeasy.domain.model.RestaurantResponse
import com.example.reserveeasy.domain.model.RestaurantsResponse
import com.example.reserveeasy.domain.model.User
import com.example.reserveeasy.domain.model.UserRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReserveEasyApiService {

    @POST("user/createUser/")
    suspend fun registerUser(@Body userRequest: UserRequest): User

    @POST("user/auth/")
    suspend fun loginUser(@Body userRequest: UserRequest): LoginResponse

    @GET("restaurant/list/")
    suspend fun getAllRestaurants(): RestaurantsResponse

    @GET("restaurant/restaurant/{id}")
    suspend fun getRestaurantById(
        @Path("id") id: String
    ): RestaurantResponse



}