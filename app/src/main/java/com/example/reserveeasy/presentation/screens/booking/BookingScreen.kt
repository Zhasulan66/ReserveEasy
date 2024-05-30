package com.example.reserveeasy.presentation.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.reserveeasy.R
import com.example.reserveeasy.common.Constants.Companion.INTER_FONT_FAMILY
import com.example.reserveeasy.data.local.LocalBookingDataProvider
import com.example.reserveeasy.domain.model.Booking
import com.example.reserveeasy.presentation.components.BookingCard
import com.example.reserveeasy.presentation.navigation.NavigationView
import com.example.reserveeasy.presentation.navigation.Screen
import com.example.reserveeasy.presentation.ui.theme.GrayEE
import com.example.reserveeasy.presentation.ui.theme.GreenMain
import com.example.reserveeasy.presentation.viewmodel.MainViewModel

@Composable
fun BookingScreen(
    navController: NavController,
) {
    val viewModel = hiltViewModel<MainViewModel>()
    //val bookingState by viewModel.bookingState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        /*when (bookingState) {
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
                val bookingList = (bookingState as Resource.Success<List<Booking>>).data
                BookingListScreen(navController, bookingList)
            }

            is Resource.Initial -> {
                viewModel.fetchAllBookingsByUser()
            }
        }*/
        BookingListScreen(
            navController,
            LocalBookingDataProvider.getBookingData()
            //emptyList()
        )

        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.BookingScreen.route) {
                        inclusive = true
                    }
                }
            },
            onBookingClick = {
                navController.navigate(Screen.BookingScreen.route) {
                    popUpTo(Screen.BookingScreen.route) {
                        inclusive = true
                    }
                }
            },
            onFavouritesClick = {
                navController.navigate(Screen.FavouriteScreen.route) {
                    popUpTo(Screen.BookingScreen.route) {
                        inclusive = true
                    }
                }
            },
            onProfileClick = {
                navController.navigate(Screen.ProfileScreen.route) {
                    popUpTo(Screen.BookingScreen.route) {
                        inclusive = true
                    }
                }
            },
            btnId = 2,
        )

    }
}

@Composable
fun BookingListScreen(
    navController: NavController,
    bookingList: List<Booking>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.my_bookings),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = INTER_FONT_FAMILY,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {

               //bookings card or empty
                if (bookingList.isNotEmpty()){
                    bookingList.forEach { booking ->
                        BookingCard(booking)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(GrayEE)
                            .padding(vertical = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = stringResource(id = R.string.no_bookings),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = INTER_FONT_FAMILY,
                            color = Color.Black
                        )
                        Text(
                            text = stringResource(id = R.string.no_bookings2),
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            fontFamily = INTER_FONT_FAMILY,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth(0.6f),
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    //add booking btn
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = GreenMain
                            )
                            .clickable {
                                navController.navigate(Screen.HomeScreen.route){
                                    popUpTo(Screen.BookingScreen.route){
                                        inclusive = true
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.add_booking),
                            fontSize = 16.sp,
                            fontFamily = INTER_FONT_FAMILY,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                }

                Spacer(modifier = Modifier.height(100.dp))
            }
        }


    }
}