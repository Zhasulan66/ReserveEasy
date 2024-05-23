package com.example.reserveeasy.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reserveeasy.R
import com.example.reserveeasy.common.Constants.Companion.INTER_FONT_FAMILY
import com.example.reserveeasy.domain.model.Booking
import com.example.reserveeasy.presentation.ui.theme.GreenMain

@Composable
fun BookingCard(
    booking: Booking
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Spacer(modifier = Modifier.height(10.dp))
        //restaurant name
        Text(
            text = "Del Papa",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = INTER_FONT_FAMILY,
            color = GreenMain
        )
        Spacer(modifier = Modifier.height(10.dp))

        //address
        Text(
            text = "Manasa 34/1",
            fontSize = 12.sp,
            fontFamily = INTER_FONT_FAMILY,
            color = Color.Gray
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
            Column{
                Image(
                    painter = painterResource(id = R.drawable.ic_date),
                    contentDescription = "icon"
                )
                Text(
                    text = "7 march",
                    fontSize = 12.sp,
                    fontFamily = INTER_FONT_FAMILY,
                    color = Color.Gray
                )
            }
            //date
            Column{
                Image(
                    painter = painterResource(id = R.drawable.ic_date),
                    contentDescription = "icon"
                )
                Text(
                    text = "11:00",
                    fontSize = 12.sp,
                    fontFamily = INTER_FONT_FAMILY,
                    color = Color.Gray
                )
            }
            //guest
            Column{
                Image(
                    painter = painterResource(id = R.drawable.ic_date),
                    contentDescription = "icon"
                )
                Text(
                    text = "2 guest",
                    fontSize = 12.sp,
                    fontFamily = INTER_FONT_FAMILY,
                    color = Color.Gray
                )
            }
        }

    }
}