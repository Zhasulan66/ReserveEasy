package com.example.reserveeasy.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.reserveeasy.domain.model.Table
import com.example.reserveeasy.presentation.ui.theme.GreenMain

@Composable
fun TableCard(
    table: Table,
    index: Int,
    currentTable: String,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(4.dp),
                color = if(currentTable == table.id) GreenMain else Color.LightGray
            )
            .background(if(currentTable == table.id) GreenMain else Color.Transparent)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Table $index",
            fontSize = 14.sp,
            fontFamily = Constants.INTER_FONT_FAMILY,
            color = if(currentTable == table.id) Color.White else Color.Gray
        )
    }
}