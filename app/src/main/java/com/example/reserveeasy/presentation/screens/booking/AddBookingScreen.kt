package com.example.reserveeasy.presentation.screens.booking

import android.app.DatePickerDialog
import android.util.Log
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
import com.example.reserveeasy.common.Constants.Companion.INTER_FONT_FAMILY
import com.example.reserveeasy.presentation.components.GuestButton
import com.example.reserveeasy.presentation.components.TimeButton
import com.example.reserveeasy.presentation.navigation.Screen
import com.example.reserveeasy.presentation.ui.theme.GreenMain
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
fun AddBookingScreen(
    navController: NavController,
    restaurantId: String,
    restaurantName: String,
    restaurantAddress: String
){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var guestAmount by remember { mutableStateOf(1)}
        var currentDate by remember { mutableStateOf("")}
        var currentTime by remember { mutableStateOf("")}

        var isDateExpanded by remember { mutableStateOf(false) }
        var isTimeExpanded by remember { mutableStateOf(false) }

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
                    fontFamily = INTER_FONT_FAMILY,
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
                                    text = if(currentDate.isEmpty()) stringResource(id = R.string.date)
                                    else currentDate,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = if(currentDate.isEmpty()) Color.Gray else Color.Black,
                                )
                            }

                            Image(
                                imageVector = if(isDateExpanded) Icons.Default.KeyboardArrowDown
                                else Icons.Rounded.KeyboardArrowRight,
                                contentDescription = "icon"
                            )

                            val calendar = Calendar.getInstance()
                            val year = calendar.get(Calendar.YEAR)
                            val month = calendar.get(Calendar.MONTH)
                            val day = calendar.get(Calendar.DAY_OF_MONTH)

                            val context = LocalContext.current
                            val datePickerDialog = DatePickerDialog(
                                context,
                                { _, selectedYear, selectedMonth, selectedDay ->
                                    currentDate = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                                }, year, month, day
                            )
                            if(isDateExpanded){
                                datePickerDialog.show()
                                datePickerDialog.setOnDateSetListener { view, myYear, myMonth, dayOfMonth ->
                                    currentDate = "$dayOfMonth.${myMonth + 1}.$myYear"
                                    datePickerDialog.hide()
                                    isDateExpanded = false
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))



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
                                .clickable {
                                    isTimeExpanded = !isTimeExpanded
                                }
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
                                    text = if(currentTime.isEmpty()) stringResource(id = R.string.time)
                                    else currentTime,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = INTER_FONT_FAMILY,
                                    color = if(currentTime.isEmpty()) Color.Gray
                                    else Color.Black,
                                )
                            }

                            Image(
                                imageVector = if(isTimeExpanded) Icons.Default.KeyboardArrowDown
                                else Icons.Rounded.KeyboardArrowRight,
                                contentDescription = "icon"
                            )
                        }

                        //show time options
                        if(isTimeExpanded){
                            Spacer(modifier = Modifier.height(10.dp))
                            //09 - 13
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                TimeButton(currentTime = currentTime, timeText = "09:00") {
                                    currentTime = "09:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "10:00") {
                                    currentTime = "10:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "11:00") {
                                    currentTime = "11:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "12:00") {
                                    currentTime = "12:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "13:00") {
                                    currentTime = "13:00"
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            //14 - 18
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                TimeButton(currentTime = currentTime, timeText = "14:00") {
                                    currentTime = "14:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "15:00") {
                                    currentTime = "15:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "16:00") {
                                    currentTime = "16:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "17:00") {
                                    currentTime = "17:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "18:00") {
                                    currentTime = "18:00"
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            //19 - 23
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                TimeButton(currentTime = currentTime, timeText = "19:00") {
                                    currentTime = "19:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "20:00") {
                                    currentTime = "20:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "21:00") {
                                    currentTime = "21:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "22:00") {
                                    currentTime = "22:00"
                                }
                                TimeButton(currentTime = currentTime, timeText = "23:00") {
                                    currentTime = "23:00"
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
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
                    if(currentDate.isNotEmpty() && currentTime.isNotEmpty()){
                        Log.d("MyTag", "current time : ${formatReservedTime(currentDate, currentTime)}")
                        navController.navigate(Screen.ChooseTableBookingScreen.route
                                + "/${restaurantId}"
                                + "/${formatReservedTime(currentDate, currentTime)}"
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
                fontFamily = INTER_FONT_FAMILY,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

fun formatReservedTime(currentDate: String, currentTime: String): String {
    // Define the formatters for parsing the input strings
    val dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    // Parse the date and time strings
    val date = LocalDate.parse(currentDate, dateFormatter)
    val time = LocalTime.parse(currentTime, timeFormatter)

    // Combine date and time into a LocalDateTime object
    val dateTime = LocalDateTime.of(date, time)

    // Define the formatter for the output string
    val outputFormatter = DateTimeFormatter.ISO_INSTANT

    // Convert LocalDateTime to an Instant with UTC offset
    val instant = dateTime.toInstant(ZoneOffset.UTC)

    // Format the Instant to the desired string
    return outputFormatter.format(instant)
}