package com.example.reserveeasy.domain.model

data class TableScheme(
    val id: String,
    val restaurantId: String,
    val length: Int,
    val width: Int,
    val active: Boolean,
    val createdAt: String,
    val updatedAt: String
)

/*"id": "ddb71a0a-1f1f-11ef-9e70-0e013fc921b0",
"restaurantId": "9db698e4-1be4-11ef-9bc3-de17b0a8f974",
"length": 255,
"width": 255,
"active": true,
"createdAt": "2024-05-31T07:31:59.598644Z",
"updatedAt": "2024-05-31T07:31:59.598644Z"*/
