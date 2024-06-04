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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reserveeasy.common.Constants
import com.example.reserveeasy.presentation.ui.theme.GreenMain

@Composable
fun TimeButton(
    currentTime: String,
    timeText: String,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .width(56.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(4.dp),
                color = if(currentTime == timeText) GreenMain else Color.LightGray
            )
            .background(if(currentTime == timeText) GreenMain else Color.Transparent)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = timeText,
            fontSize = 14.sp,
            fontFamily = Constants.INTER_FONT_FAMILY,
            color = if(currentTime == timeText) Color.White else Color.Gray
        )
    }
}