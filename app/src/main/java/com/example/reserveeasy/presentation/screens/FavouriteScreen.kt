package com.example.reserveeasy.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.reserveeasy.R
import com.example.reserveeasy.common.Constants
import com.example.reserveeasy.data.local.LocalRestaurantDataProvider
import com.example.reserveeasy.domain.model.Restaurant
import com.example.reserveeasy.presentation.components.FavouriteCard
import com.example.reserveeasy.presentation.navigation.NavigationView
import com.example.reserveeasy.presentation.navigation.Screen
import com.example.reserveeasy.presentation.ui.theme.GrayF4
import com.example.reserveeasy.presentation.viewmodel.MainViewModel

@Composable
fun FavouriteScreen(
    navController: NavController,
) {
    val viewModel = hiltViewModel<MainViewModel>()
    //val restaurantState by viewModel.restaurantState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        /*when (restaurantState) {
            is Resource.Loading -> {
                LoadingScreen()
            }

            is Resource.Error -> {
                ErrorScreen(
                    modifier = Modifier,
                    retryAction = {
                        navController.navigate(route = navController.currentDestination?.route ?: ""){
                            popUpTo(navController.previousBackStackEntry?.destination?.id ?: return@navigate)
                        }
                    }
                )
            }

            is Resource.Success -> {
                val restaurantList = (restaurantState as Resource.Success<List<Restaurant>>).data
                RestaurantListScreen(navController, restaurantList)
            }

            is Resource.Initial -> {
                viewModel.fetchAllRestaurants()
            }
        }*/
        FavouriteListScreen(navController, LocalRestaurantDataProvider.getRestaurantData())

        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.FavouriteScreen.route) {
                        inclusive = true
                    }
                }
            },
            onBookingClick = {
                navController.navigate(Screen.BookingScreen.route) {
                    popUpTo(Screen.FavouriteScreen.route) {
                        inclusive = true
                    }
                }
            },
            onFavouritesClick = {
                navController.navigate(Screen.FavouriteScreen.route) {
                    popUpTo(Screen.FavouriteScreen.route) {
                        inclusive = true
                    }
                }
            },
            onProfileClick = {
                navController.navigate(Screen.ProfileScreen.route) {
                    popUpTo(Screen.FavouriteScreen.route) {
                        inclusive = true
                    }
                }
            },
            btnId = 3,
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteListScreen(
    navController: NavController,
    restaurantList: List<Restaurant>
) {
    var userSearch by remember { mutableStateOf("") }

    val sortedRestaurantList =
        if(userSearch.isNotEmpty()) {
            restaurantList.filter { restaurant ->
                restaurant.name.startsWith(userSearch, ignoreCase = true)
            }
        } else restaurantList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.favourites),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constants.INTER_FONT_FAMILY,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(10.dp))

        //search field
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = userSearch,
                onValueChange = { text ->
                    userSearch = text
                },
                textStyle = TextStyle(
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(24.dp)),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search),
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                },
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .fillMaxHeight()
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "icon",
                            tint = Color.DarkGray
                        )
                    }
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = GrayF4,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Image(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "img",
                modifier = Modifier.clickable {
                    navController.navigate(Screen.FilterScreen.route)
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {

                for (i in sortedRestaurantList.indices step 2){
                    if(i + 1 < sortedRestaurantList.size){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            FavouriteCard(restaurant = sortedRestaurantList[i]) {
                                navController.navigate(Screen.RestaurantInfoScreen.route + "/1")
                            }
                            FavouriteCard(restaurant = sortedRestaurantList[i+1]) {
                                navController.navigate(Screen.RestaurantInfoScreen.route + "/1")
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    else {
                        FavouriteCard(restaurant = sortedRestaurantList[i]) {
                            navController.navigate(Screen.RestaurantInfoScreen.route + "/1")
                        }
                    }

                }

                Spacer(modifier = Modifier.height(100.dp))
            }
        }


    }
}