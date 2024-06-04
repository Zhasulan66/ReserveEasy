package com.example.reserveeasy.domain.model

data class Table(
    val id: String,
    val tableSchemeId: String,
    val x: Int,
    val xSize: Int,
    val y: Int,
    val ySize: Int,
    val personsLimit: Int,
    val createdAt: String,
    val updatedAt: String
)

/*"id": "3510a67e-1f20-11ef-9e70-0e013fc921b0",
"tableSchemeId": "ddb71a0a-1f1f-11ef-9e70-0e013fc921b0",
"x": 15,
"xSize": 150,
"y": 15,
"ySize": 200,
"personsLimit": 0,
"createdAt": "2024-05-31T07:34:26.147289Z",
"updatedAt": "2024-05-31T07:34:26.147289Z"*/
