package com.example.reserveeasy.data.remote

import com.example.reserveeasy.domain.model.BookingIdResponse
import com.example.reserveeasy.domain.model.BookingRequest
import com.example.reserveeasy.domain.model.BookingResponse
import com.example.reserveeasy.domain.model.LoginResponse
import com.example.reserveeasy.domain.model.RestaurantResponse
import com.example.reserveeasy.domain.model.RestaurantsResponse
import com.example.reserveeasy.domain.model.TableSchemeResponse
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

    @GET("table-scheme/scheme/{restaurantId}")
    suspend fun getTableScheme(
        @Path("restaurantId") restaurantId: String
    ) : TableSchemeResponse

    @GET("booking/list")
    suspend fun getAllBookings() : BookingResponse

    @POST("booking/create/")
    suspend fun createBooking(
        @Body bookingRequest: BookingRequest
    ) : BookingIdResponse




}