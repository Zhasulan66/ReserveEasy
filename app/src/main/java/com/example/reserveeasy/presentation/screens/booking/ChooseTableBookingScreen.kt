package com.example.reserveeasy.presentation.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.reserveeasy.common.Constants
import com.example.reserveeasy.domain.model.Booking
import com.example.reserveeasy.domain.model.BookingResponse
import com.example.reserveeasy.domain.model.Resource
import com.example.reserveeasy.domain.model.Table
import com.example.reserveeasy.domain.model.TableSchemeResponse
import com.example.reserveeasy.presentation.components.BookingCard
import com.example.reserveeasy.presentation.components.TableCard
import com.example.reserveeasy.presentation.navigation.NavigationView
import com.example.reserveeasy.presentation.navigation.Screen
import com.example.reserveeasy.presentation.screens.ErrorScreen
import com.example.reserveeasy.presentation.screens.LoadingScreen
import com.example.reserveeasy.presentation.ui.theme.GrayEE
import com.example.reserveeasy.presentation.ui.theme.GreenMain
import com.example.reserveeasy.presentation.viewmodel.MainViewModel

@Composable
fun ChooseTableBookingScreen(
    navController: NavController,
    restaurantId: String,
    reservedTime: String,
    restaurantName: String,
    restaurantAddress: String
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val tableSchemeState by viewModel.tableSchemeState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        when (tableSchemeState) {
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
                val tableSchemeResponse = (tableSchemeState as Resource.Success<TableSchemeResponse>).data
                val tableList = tableSchemeResponse.tables
                TableListScreen(navController, tableList, restaurantId, reservedTime,
                    restaurantName, restaurantAddress)
            }

            is Resource.Initial -> {
                viewModel.fetchTableScheme(restaurantId)
            }
        }

    }
}

@Composable
fun TableListScreen(
    navController: NavController,
    tableList: List<Table>,
    restaurantId: String,
    reservedTime: String,
    restaurantName: String,
    restaurantAddress: String
) {
    var currentTable by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.choose_table),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Constants.INTER_FONT_FAMILY,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {

                    //bookings card or empty
                    if (tableList.isNotEmpty()){
                        tableList.forEachIndexed { index, table ->
                            TableCard(table, index+1, currentTable){
                                currentTable = table.id
                            }
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
                                text = stringResource(id = R.string.no_tables),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = Constants.INTER_FONT_FAMILY,
                                color = Color.Black
                            )
                            Text(
                                text = stringResource(id = R.string.no_tables2),
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                fontFamily = Constants.INTER_FONT_FAMILY,
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
                                fontFamily = Constants.INTER_FONT_FAMILY,
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
                    if(currentTable.isNotEmpty()){
                        navController.navigate(Screen.ConfirmBookingScreen.route
                                + "/${restaurantId}"
                                + "/${currentTable}"
                                + "/${reservedTime}"
                            + "/${restaurantName}"
                            + "/${restaurantAddress}"
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