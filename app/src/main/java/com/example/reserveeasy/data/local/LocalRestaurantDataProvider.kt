package com.example.reserveeasy.data.local

import com.example.reserveeasy.domain.model.Restaurant

object LocalRestaurantDataProvider{
    val defaultRestaurant = getRestaurantData()[0]

    fun getRestaurantData(): List<Restaurant> {
        return listOf(
            Restaurant(
                id = "1",
                name = "Del Papa",
                phone = "8777 666 55 44",
                address = "Manasa 44/3",
                socialMediaLink = "https://someLink",
                createdAt = "2024-05-22T07:09:02.852Z",
                updatedAt = "2024-05-22T07:09:02.852Z"
            ),
            Restaurant(
                id = "2",
                name = "Aurora",
                phone = "8777 666 55 44",
                address = "Manasa 44/3",
                socialMediaLink = "https://someLink",
                createdAt = "2024-05-22T07:09:02.852Z",
                updatedAt = "2024-05-22T07:09:02.852Z"
            ),
            Restaurant(
                id = "3",
                name = "Chacha",
                phone = "8777 666 55 44",
                address = "Manasa 44/3",
                socialMediaLink = "https://someLink",
                createdAt = "2024-05-22T07:09:02.852Z",
                updatedAt = "2024-05-22T07:09:02.852Z"
            ),
            Restaurant(
                id = "4",
                name = "SomeTea",
                phone = "8777 666 55 44",
                address = "Manasa 44/3",
                socialMediaLink = "https://someLink",
                createdAt = "2024-05-22T07:09:02.852Z",
                updatedAt = "2024-05-22T07:09:02.852Z"
            ),
            Restaurant(
                id = "5",
                name = "Cactus",
                phone = "8777 666 55 44",
                address = "Manasa 44/3",
                socialMediaLink = "https://someLink",
                createdAt = "2024-05-22T07:09:02.852Z",
                updatedAt = "2024-05-22T07:09:02.852Z"
            )
        )
    }
}