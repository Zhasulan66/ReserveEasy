package com.example.reserveeasy.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reserveeasy.presentation.LanguageManager
import com.example.reserveeasy.presentation.screens.HomeScreen
import com.example.reserveeasy.presentation.screens.SplashScreen
import com.example.reserveeasy.presentation.screens.auth.LoginScreen
import com.example.reserveeasy.presentation.screens.auth.RegistrationScreen

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
    }
}