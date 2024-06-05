package com.example.reserveeasy.presentation.screens.booking

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.reserveeasy.R
import com.example.reserveeasy.common.Constants
import com.example.reserveeasy.domain.model.BookingIdResponse
import com.example.reserveeasy.domain.model.BookingRequest
import com.example.reserveeasy.domain.model.BookingResponse
import com.example.reserveeasy.domain.model.Resource
import com.example.reserveeasy.presentation.components.formatDate
import com.example.reserveeasy.presentation.navigation.Screen
import com.example.reserveeasy.presentation.screens.ErrorScreen
import com.example.reserveeasy.presentation.screens.LoadingScreen
import com.example.reserveeasy.presentation.ui.theme.GreenMain
import com.example.reserveeasy.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun ConfirmBookingScreen(
    navController: NavController,
    restaurantId: String,
    tableId: String,
    reservedTime: String,
    restaurantName: String,
    restaurantAddress: String
){

    val viewModel = hiltViewModel<MainViewModel>()
    val createBookingState by viewModel.createBookingState.collectAsState()
    val savedUserId: String? by viewModel.readUserId().collectAsState(initial = null)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        when (createBookingState) {
            is Resource.Loading -> {
                LoadingScreen()
            }

            is Resource.Error -> {
                ErrorScreen(
                    modifier = Modifier,
                    retryAction = {
                        navController.navigate(
                            route = navController.currentDestination?.route ?: ""
                        ) {
                            popUpTo(
                                navController.previousBackStackEntry?.destination?.id
                                    ?: return@navigate
                            )
                        }
                    }
                )
            }

            is Resource.Success -> {
                val bookingIdResponse = (createBookingState as Resource.Success<BookingIdResponse>).data
                navController.navigate(Screen.BookingScreen.route)
                Toast.makeText(
                    LocalContext.current,
                    stringResource(id = R.string.booking_success),
                    Toast.LENGTH_SHORT).show()
                viewModel.createBookingSuccess()
            }

            is Resource.Initial -> {
                ConfirmBookingInitial(
                    navController, reservedTime,
                    restaurantName, restaurantAddress
                )
            }
        }

        //Book table btn
        Box(
            modifier = Modifier
                .offset(y = (-10).dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(52.dp)
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    color = GreenMain
                )
                .clickable {
                    savedUserId?.let {
                        viewModel.createBooking(
                            BookingRequest(
                                userId = it,
                                restaurantId = restaurantId,
                                tableId = tableId,
                                reservedTime = reservedTime
                            )
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.book_table),
                fontSize = 16.sp,
                fontFamily = Constants.INTER_FONT_FAMILY,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }






}

@Composable
fun ConfirmBookingInitial(
    navController: NavController,
    reservedTime: String,
    restaurantName: String,
    restaurantAddress: String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        //Screen Name
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            var canNavigate by remember { mutableStateOf(true) }
            //back btn
            Image(
                imageVector = Icons.Rounded.KeyboardArrowLeft,
                contentDescription = "icon",
                modifier = Modifier
                    .clickable {
                        if (canNavigate) {
                            canNavigate = false
                            navController.popBackStack()
                        }
                    }
            )
            if (!canNavigate) {
                LaunchedEffect(Unit) {
                    delay(1000)
                    canNavigate = true
                }
            }

            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.confirm_booking),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Constants.INTER_FONT_FAMILY,
                color = Color.Black,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ){
            item {

                //restaurant name
                Text(
                    text = restaurantName,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))

                //time
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    //date
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.ic_date),
                            contentDescription = "icon"
                        )
                        Text(
                            text = formatDate(reservedTime.substring(0, 10)),
                            fontSize = 12.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            color = Color.Gray
                        )
                    }
                    //date
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.ic_date),
                            contentDescription = "icon"
                        )
                        Text(
                            text = reservedTime.substring(11, 16),
                            fontSize = 12.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            color = Color.Gray
                        )
                    }
                    //guest
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.ic_date),
                            contentDescription = "icon"
                        )
                        Text(
                            text = "2 guest",
                            fontSize = 12.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                //restaurant address
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_address),
                        contentDescription = "icon"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = restaurantAddress,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Gray,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                //deposit
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic_deposit),
                            contentDescription = "icon"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(id = R.string.deposit),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            color = Color.Gray,
                        )
                    }

                    Text(
                        text = "5000 ₸",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = GreenMain,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))


                Spacer(modifier = Modifier.height(100.dp))

            }
        }

    }
}