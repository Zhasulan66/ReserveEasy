package com.example.reserveeasy.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.reserveeasy.presentation.screens.FavouriteScreen
import com.example.reserveeasy.presentation.LanguageManager
import com.example.reserveeasy.presentation.screens.HomeScreen
import com.example.reserveeasy.presentation.screens.profile.ProfileScreen
import com.example.reserveeasy.presentation.screens.RestaurantInfoScreen
import com.example.reserveeasy.presentation.screens.SplashScreen
import com.example.reserveeasy.presentation.screens.auth.LoginScreen
import com.example.reserveeasy.presentation.screens.auth.RegistrationScreen
import com.example.reserveeasy.presentation.screens.profile.NotificationSettingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val languageManager = LanguageManager(LocalContext.current)


    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        //SplashScreen
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                navController = navController
            )
        }

        //LoginScreen
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                navController = navController,
            )
        }

        //RegistrationScreen
        composable(route = Screen.RegistrationScreen.route) {
            RegistrationScreen(
                navController = navController
            )
        }

        //HomeScreen
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                navController = navController,
            )
        }

        //RestaurantInfoScreen
        composable(route = Screen.RestaurantInfoScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { entry ->
            RestaurantInfoScreen(
                navController = navController,
                restaurantId = entry.arguments!!.getString("id").toString()
            )
        }

        //ProfileScreen
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(
                navController = navController,
                languageManager = languageManager
            )
        }

        //NotificationSettingScreen
        composable(route = Screen.NotificationSettingScreen.route) {
            NotificationSettingScreen(
                navController = navController,
            )
        }

        //FavouriteScreen
        composable(route = Screen.FavouriteScreen.route) {
            FavouriteScreen(
                navController = navController,
            )
        }
    }
}