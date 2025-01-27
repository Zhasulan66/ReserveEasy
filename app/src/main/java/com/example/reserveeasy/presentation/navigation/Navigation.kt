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
import com.example.reserveeasy.presentation.screens.booking.BookingScreen
import com.example.reserveeasy.presentation.screens.HomeScreen
import com.example.reserveeasy.presentation.screens.profile.ProfileScreen
import com.example.reserveeasy.presentation.screens.RestaurantInfoScreen
import com.example.reserveeasy.presentation.screens.SplashScreen
import com.example.reserveeasy.presentation.screens.auth.LoginScreen
import com.example.reserveeasy.presentation.screens.auth.RegistrationScreen
import com.example.reserveeasy.presentation.screens.booking.AddBookingScreen
import com.example.reserveeasy.presentation.screens.booking.ChooseTableBookingScreen
import com.example.reserveeasy.presentation.screens.booking.ConfirmBookingScreen
import com.example.reserveeasy.presentation.screens.filter.FilterScreen
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

        //FilterScreen
        composable(route = Screen.FilterScreen.route) {
            FilterScreen(
                navController = navController,
            )
        }

        //BookingScreen
        composable(route = Screen.BookingScreen.route) {
            BookingScreen(
                navController = navController,
            )
        }

        //AddBookingScreen
        composable(route = Screen.AddBookingScreen.route
                + "/{id}" + "/{restaurantName}" + "/{restaurantAddress}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("restaurantName"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("restaurantAddress"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { entry ->
            AddBookingScreen(
                navController = navController,
                restaurantId = entry.arguments!!.getString("id").toString(),
                restaurantName = entry.arguments!!.getString("restaurantName").toString(),
                restaurantAddress = entry.arguments!!.getString("restaurantAddress").toString()
            )
        }

        //ChooseTableBookingScreen
        composable(route = Screen.ChooseTableBookingScreen.route
                + "/{id}" + "/{reservedTime}" + "/{restaurantName}" + "/{restaurantAddress}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("reservedTime"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("restaurantName"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("restaurantAddress"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { entry ->
            ChooseTableBookingScreen(
                navController = navController,
                restaurantId = entry.arguments!!.getString("id").toString(),
                reservedTime = entry.arguments!!.getString("reservedTime").toString(),
                restaurantName = entry.arguments!!.getString("restaurantName").toString(),
                restaurantAddress = entry.arguments!!.getString("restaurantAddress").toString()
            )
        }

        //ConfirmBookingScreen
        composable(route = Screen.ConfirmBookingScreen.route
                + "/{id}" + "/{tableId}" + "/{reservedTime}"
                + "/{restaurantName}" + "/{restaurantAddress}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("tableId"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("reservedTime"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("restaurantName"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("restaurantAddress"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { entry ->
            ConfirmBookingScreen(
                navController = navController,
                restaurantId = entry.arguments!!.getString("id").toString(),
                tableId = entry.arguments!!.getString("tableId").toString(),
                reservedTime = entry.arguments!!.getString("reservedTime").toString(),
                restaurantName = entry.arguments!!.getString("restaurantName").toString(),
                restaurantAddress = entry.arguments!!.getString("restaurantAddress").toString()
            )
        }


    }
}