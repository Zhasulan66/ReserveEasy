package com.example.reserveeasy.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reserveeasy.common.Constants
import com.example.reserveeasy.presentation.ui.theme.GreenMain

@Composable
fun GuestButton(
    number: Int,
    guestAmount: Int,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .width(48.dp)
            .height(36.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = if(guestAmount == number) GreenMain else Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .background(if(guestAmount == number) GreenMain else Color.Transparent)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "$number",
            fontSize = 14.sp,
            fontFamily = Constants.INTER_FONT_FAMILY,
            fontWeight = FontWeight.SemiBold,
            color = if(guestAmount == number) Color.White else Color.Black
        )
    }
}
