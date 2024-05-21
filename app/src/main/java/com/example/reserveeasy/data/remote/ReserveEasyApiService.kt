package com.example.reserveeasy.data.remote

import com.example.reserveeasy.domain.model.LoginResponse
import com.example.reserveeasy.domain.model.User
import com.example.reserveeasy.domain.model.UserRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ReserveEasyApiService {

    @POST("user/createUser/")
    suspend fun registerUser(@Body userRequest: UserRequest): User

    @POST("user/auth/")
    suspend fun loginUser(@Body userRequest: UserRequest): LoginResponse

}