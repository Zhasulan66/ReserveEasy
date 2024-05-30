package com.example.reserveeasy.presentation.screens

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
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.reserveeasy.data.local.LocalRestaurantDataProvider
import com.example.reserveeasy.domain.model.Resource
import com.example.reserveeasy.domain.model.Restaurant
import com.example.reserveeasy.domain.model.RestaurantResponse
import com.example.reserveeasy.presentation.navigation.Screen
import com.example.reserveeasy.presentation.ui.theme.GreenLight
import com.example.reserveeasy.presentation.ui.theme.GreenMain
import com.example.reserveeasy.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun RestaurantInfoScreen(
    navController: NavController,
    restaurantId: String
){
    val viewModel = hiltViewModel<MainViewModel>()
    val restaurantInfoState by viewModel.restaurantInfoState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (restaurantInfoState) {
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
                val restaurant = (restaurantInfoState as Resource.Success<RestaurantResponse>).data
                RestaurantSuccessScreen(
                    navController = navController,
                    restaurant = restaurant.restaurant
                )
            }

            is Resource.Initial -> {
                viewModel.fetchRestaurantById(restaurantId)
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
                    navController.navigate(Screen.AddBookingScreen.route)
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
fun RestaurantSuccessScreen(
    navController: NavController,
    restaurant: Restaurant
) {
    var isAboutExpanded by remember { mutableStateOf(false) }
    var isMapExpanded by remember { mutableStateOf(false) }
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
                text = stringResource(id = R.string.restaurant_information),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Constants.INTER_FONT_FAMILY,
                color = Color.Black,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ){
            item {
                //restaurant image
                Image(painter = painterResource(
                    id = R.drawable.delpapa),
                    contentDescription = "img",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ){

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ){
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Column {
                                //restaurant name
                                Text(
                                    text = restaurant.name,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = Constants.INTER_FONT_FAMILY,
                                    color = Color.Black,
                                )
                                //restaurant type
                                Text(
                                    text = stringResource(id = R.string.cafe),
                                    fontSize = 16.sp,
                                    fontFamily = Constants.INTER_FONT_FAMILY,
                                    color = GreenMain,
                                )
                            }
                            var isLiked by remember { mutableStateOf(false) }
                            //like image
                            Image(
                                painter = painterResource(id = if(isLiked) R.drawable.ic_like_on
                                else R.drawable.ic_like_off),
                                contentDescription = "icon",
                                modifier = Modifier
                                    .clickable {
                                        isLiked = !isLiked
                                    }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        //restaurant rating
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_star),
                                contentDescription = "icon"
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "4.8",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = Constants.INTER_FONT_FAMILY,
                                color = Color.Gray,
                            )
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
                                text = restaurant.address,
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

                        //Menu button
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { }
                                .background(
                                    Color(
                                        GreenLight.red,
                                        GreenLight.green,
                                        GreenLight.blue,
                                        alpha = 0.26f
                                    )
                                )
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_menu),
                                    contentDescription = "icon"
                                )
                                Text(
                                    text = stringResource(id = R.string.menu),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = Constants.INTER_FONT_FAMILY,
                                    color = Color.Black,
                                )
                            }

                            Image(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "icon")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ){
                        Spacer(modifier = Modifier.height(10.dp))
                        //About restaurant
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_about),
                                    contentDescription = "icon"
                                )
                                Text(
                                    text = stringResource(id = R.string.about_restaurant),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = Constants.INTER_FONT_FAMILY,
                                    color = Color.Black,
                                )
                            }

                            Image(
                                imageVector = if(isAboutExpanded) Icons.Rounded.KeyboardArrowDown
                                else Icons.Rounded.KeyboardArrowRight,
                                contentDescription = "icon",
                                modifier = Modifier
                                    .clickable {
                                        isAboutExpanded = !isAboutExpanded
                                    }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .padding(horizontal = 10.dp)
                                .background(Color.LightGray)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        if(isAboutExpanded){
                            Text(
                                text = stringResource(R.string.lorem_ipsum),
                                fontSize = 16.sp,
                                fontFamily = Constants.INTER_FONT_FAMILY,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .padding(horizontal = 10.dp)
                                    .background(Color.LightGray)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        //map
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_map),
                                    contentDescription = "icon"
                                )
                                Text(
                                    text = stringResource(id = R.string.map),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = Constants.INTER_FONT_FAMILY,
                                    color = Color.Black,
                                )
                            }

                            Image(
                                imageVector = if(isMapExpanded) Icons.Rounded.KeyboardArrowDown
                                else Icons.Rounded.KeyboardArrowRight,
                                contentDescription = "icon",
                                modifier = Modifier
                                    .clickable {
                                        isMapExpanded = !isMapExpanded
                                    }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        if(isMapExpanded){
                            Image(
                                painter = painterResource(R.drawable.delpapa),
                                contentDescription = "img",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp),
                                contentScale = ContentScale.FillWidth
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))

            }
        }

    }
}
