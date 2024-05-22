package com.example.reserveeasy.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reserveeasy.R
import com.example.reserveeasy.common.Constants.Companion.INTER_FONT_FAMILY
import com.example.reserveeasy.domain.model.Restaurant

@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit
){
    var isLiked by remember { mutableStateOf(false) }
    Column(
       modifier = Modifier
           .width(200.dp)
           .clickable {
               onClick()
           }
    ){
        //restaurant img
        Image(
            painter = painterResource(id = R.drawable.delpapa),
            contentDescription = "img",
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            ){
                //name
                Text(
                    text = restaurant.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = INTER_FONT_FAMILY,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(4.dp))

                //rating
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "icon"
                    )
                    Text(
                        text = "4.8",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = INTER_FONT_FAMILY,
                        color = Color.Gray,
                    )
                }
            }

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

    }
}