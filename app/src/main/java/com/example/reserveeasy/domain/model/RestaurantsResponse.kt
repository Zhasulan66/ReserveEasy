package com.example.reserveeasy.domain.model

data class RestaurantsResponse(
    val totalHits: Int,
    val restaurants: List<Restaurant>
)

data class RestaurantResponse(
    val restaurant: Restaurant
)
