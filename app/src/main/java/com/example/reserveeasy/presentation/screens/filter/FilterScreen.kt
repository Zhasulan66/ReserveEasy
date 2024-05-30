package com.example.reserveeasy.presentation.screens.filter

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reserveeasy.R
import com.example.reserveeasy.common.Constants
import com.example.reserveeasy.common.Constants.Companion.INTER_FONT_FAMILY
import com.example.reserveeasy.common.Establishment
import com.example.reserveeasy.common.Kitchen
import com.example.reserveeasy.presentation.navigation.Screen
import com.example.reserveeasy.presentation.ui.theme.GreenLight
import com.example.reserveeasy.presentation.ui.theme.GreenMain
import kotlinx.coroutines.delay

@Composable
fun FilterScreen(
    navController: NavController
) {
    var isTypeExpanded by remember { mutableStateOf(false) }
    var isKitchenExpanded by remember { mutableStateOf(false) }

    var currentMinValue by remember { mutableStateOf(2000f) }
    var currentMaxValue by remember { mutableStateOf(30000f) }

    var currentEstablishment by remember { mutableStateOf(Establishment.None) }
    var currentKitchen by remember { mutableStateOf(Kitchen.None) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            //Screen Name
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
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
                        text = stringResource(id = R.string.filter),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                }

                //reset btn
                Text(
                    text = stringResource(id = R.string.reset),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = INTER_FONT_FAMILY,
                    color = GreenMain,
                    modifier = Modifier
                        .clickable {
                            currentEstablishment = Establishment.None
                            currentKitchen = Kitchen.None
                            currentMaxValue = 30000f
                        }
                )

            }
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    //average check
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_money),
                                    contentDescription = "icon"
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = stringResource(id = R.string.average_check),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Black,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
                           Text(
                               text = "${currentMinValue.toInt()} tg - ${currentMaxValue.toInt()} tg",
                               fontSize = 14.sp,
                               fontFamily = INTER_FONT_FAMILY,
                               color = Color.Black
                           )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                        ) {
                            Slider(
                                value = currentMaxValue,
                                onValueChange = {
                                    if (it >= currentMinValue) {
                                        currentMaxValue = it
                                    }
                                },
                                valueRange = 2000f..30000f,
                                colors = SliderDefaults.colors(
                                    thumbColor = GreenMain,
                                    activeTrackColor = GreenMain,
                                    inactiveTrackColor = GreenLight,

                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    //type
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_type),
                                    contentDescription = "icon"
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = stringResource(id = R.string.type_of_establishment),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Black,
                                )
                            }

                            Image(
                                imageVector = if(isTypeExpanded) Icons.Rounded.KeyboardArrowDown
                                else Icons.Rounded.KeyboardArrowRight,
                                contentDescription = "icon",
                                modifier = Modifier
                                    .clickable {
                                        isTypeExpanded = !isTypeExpanded
                                    }
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    //type options
                    if (isTypeExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(10.dp)
                        ) {
                            Spacer(modifier = Modifier.height(10.dp))
                            //restaurant
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        currentEstablishment = Establishment.Restaurant
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.restaurant),
                                    fontSize = 16.sp,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Black
                                )
                                Image(
                                    painter = painterResource(
                                        id = if (currentEstablishment == Establishment.Restaurant)
                                            R.drawable.radio_on else R.drawable.radio_off
                                    ),
                                    contentDescription = "icon",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.LightGray)
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            //cafe
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        currentEstablishment = Establishment.Cafe
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.cafe),
                                    fontSize = 16.sp,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Black
                                )
                                Image(
                                    painter = painterResource(
                                        id = if (currentEstablishment == Establishment.Cafe)
                                            R.drawable.radio_on else R.drawable.radio_off
                                    ),
                                    contentDescription = "icon",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.LightGray)
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            //bar
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        currentEstablishment = Establishment.Bar
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.bar),
                                    fontSize = 16.sp,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Black
                                )
                                Image(
                                    painter = painterResource(
                                        id = if (currentEstablishment == Establishment.Bar)
                                            R.drawable.radio_on else R.drawable.radio_off
                                    ),
                                    contentDescription = "icon",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.LightGray)
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            //lounge
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        currentEstablishment = Establishment.Lounge
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.lounge),
                                    fontSize = 16.sp,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Black
                                )
                                Image(
                                    painter = painterResource(
                                        id = if (currentEstablishment == Establishment.Lounge)
                                            R.drawable.radio_on else R.drawable.radio_off
                                    ),
                                    contentDescription = "icon",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    //kitchen
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_kitchen),
                                    contentDescription = "icon"
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = stringResource(id = R.string.kitchen),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Black,
                                )
                            }

                            Image(
                                imageVector = if(isKitchenExpanded) Icons.Rounded.KeyboardArrowDown
                                else Icons.Rounded.KeyboardArrowRight,
                                contentDescription = "icon",
                                modifier = Modifier
                                    .clickable {
                                        isKitchenExpanded = !isKitchenExpanded
                                    }
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    //kitchen options
                    if (isKitchenExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(10.dp)
                        ) {
                            Spacer(modifier = Modifier.height(10.dp))
                            //turkish
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        currentKitchen = Kitchen.Turkish
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.kitchen_turkish),
                                    fontSize = 16.sp,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Black
                                )
                                Image(
                                    painter = painterResource(
                                        id = if (currentKitchen == Kitchen.Turkish)
                                            R.drawable.radio_on else R.drawable.radio_off
                                    ),
                                    contentDescription = "icon",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.LightGray)
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            //georgian
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        currentKitchen = Kitchen.Georgian
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.kitchen_georgian),
                                    fontSize = 16.sp,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Black
                                )
                                Image(
                                    painter = painterResource(
                                        id = if (currentKitchen == Kitchen.Georgian)
                                            R.drawable.radio_on else R.drawable.radio_off
                                    ),
                                    contentDescription = "icon",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.LightGray)
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            //korean
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        currentKitchen = Kitchen.Korean
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.kitchen_korean),
                                    fontSize = 16.sp,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Black
                                )
                                Image(
                                    painter = painterResource(
                                        id = if (currentKitchen == Kitchen.Korean)
                                            R.drawable.radio_on else R.drawable.radio_off
                                    ),
                                    contentDescription = "icon",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.LightGray)
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            //kazakh
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        currentKitchen = Kitchen.Kazakh
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.kitchen_kazakh),
                                    fontSize = 16.sp,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Black
                                )
                                Image(
                                    painter = painterResource(
                                        id = if (currentKitchen == Kitchen.Kazakh)
                                            R.drawable.radio_on else R.drawable.radio_off
                                    ),
                                    contentDescription = "icon",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    //payment
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_payment),
                                    contentDescription = "icon"
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = stringResource(id = R.string.payment_method),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = Constants.INTER_FONT_FAMILY,
                                    color = Color.Black,
                                )
                            }

                            Image(
                                imageVector = Icons.Rounded.KeyboardArrowRight,
                                contentDescription = "icon",
                                modifier = Modifier
                                    .clickable {

                                    }
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        //show result btn
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

                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.show_result),
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