package com.example.reserveeasy.data.repository

import com.example.reserveeasy.data.remote.ReserveEasyApiService
import com.example.reserveeasy.domain.model.LoginResponse
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
}