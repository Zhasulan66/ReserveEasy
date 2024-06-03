package com.example.reserveeasy.presentation.screens.booking

import android.app.DatePickerDialog
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.reserveeasy.presentation.components.GuestButton
import com.example.reserveeasy.presentation.ui.theme.GreenMain
import kotlinx.coroutines.delay
import java.util.Calendar

@Composable
fun AddBookingScreen(
    navController: NavController,
){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var guestAmount by remember { mutableStateOf(1)}
        var date by remember { mutableStateOf("")}
        var time by remember { mutableStateOf("17:00")}

        var isDateExpanded by remember { mutableStateOf(false) }

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
                    text = stringResource(id = R.string.bookings),
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

                    //number of quests
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(10.dp)
                    ){
                       Text(
                           text = stringResource(R.string.number_of_guests),
                           fontFamily = INTER_FONT_FAMILY,
                           fontSize = 16.sp,
                           fontWeight = FontWeight.Bold,
                           color = Color.Black
                       )
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            GuestButton(1, guestAmount){ guestAmount = 1 }
                            GuestButton(2, guestAmount){ guestAmount = 2 }
                            GuestButton(3, guestAmount){ guestAmount = 3 }
                            GuestButton(4, guestAmount){ guestAmount = 4 }
                            GuestButton(5, guestAmount){ guestAmount = 5 }
                            GuestButton(6, guestAmount){ guestAmount = 6 }
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = stringResource(R.string.call_restaurant),
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            fontFamily = INTER_FONT_FAMILY,
                            color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    //date and time
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(10.dp)
                    ){
                        //date
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable { isDateExpanded = !isDateExpanded }
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_date),
                                    contentDescription = "icon"
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if(date.isEmpty()) stringResource(id = R.string.date)
                                    else date,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = if(date.isEmpty()) Color.Gray else Color.Black,
                                )
                            }

                            Image(
                                imageVector = if(isDateExpanded) Icons.Default.KeyboardArrowDown
                                else Icons.Rounded.KeyboardArrowRight,
                                contentDescription = "icon"
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        if(isDateExpanded){
                            val calendar = Calendar.getInstance()
                            val year = calendar.get(Calendar.YEAR)
                            val month = calendar.get(Calendar.MONTH)
                            val day = calendar.get(Calendar.DAY_OF_MONTH)

                            val context = LocalContext.current
                            val datePickerDialog = DatePickerDialog(
                                context,
                                { _, selectedYear, selectedMonth, selectedDay ->
                                    date = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                                }, year, month, day
                            )
                            datePickerDialog.show()
                        }

                        //time
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable { }
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_time),
                                    contentDescription = "icon"
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = stringResource(id = R.string.time),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = Color.Gray,
                                )
                            }

                            Image(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "icon")
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

                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.book_table),
                fontSize = 16.sp,
                fontFamily = INTER_FONT_FAMILY,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}