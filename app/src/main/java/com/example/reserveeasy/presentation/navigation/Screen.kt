package com.example.reserveeasy.presentation.navigation

sealed class Screen(val route: String) {

    data object SplashScreen: Screen("splash_screen")

    data object LoginScreen : Screen("login_screen")

    data object RegistrationScreen : Screen("registration_screen")

    data object HomeScreen : Screen("home_screen")
    data object RestaurantInfoScreen : Screen("restaurant_info_screen")

    data object ProfileScreen : Screen("profile_screen")
    data object NotificationSettingScreen : Screen("notification_setting_screen")

    data object FavouriteScreen : Screen("favourite_screen")

}