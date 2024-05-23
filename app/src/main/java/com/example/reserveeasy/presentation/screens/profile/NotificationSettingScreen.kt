package com.example.reserveeasy.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reserveeasy.R
import com.example.reserveeasy.common.Constants
import com.example.reserveeasy.common.Constants.Companion.INTER_FONT_FAMILY
import com.example.reserveeasy.presentation.ui.theme.GreenLight
import com.example.reserveeasy.presentation.ui.theme.GreenMain
import kotlinx.coroutines.delay

@Composable
fun NotificationSettingScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            var canNavigate by remember { mutableStateOf(true) }
            //Screen Name
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
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

                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.notifications),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = INTER_FONT_FAMILY,
                    color = Color.Black,
                )
            }
            if (!canNavigate) {
                LaunchedEffect(Unit) {
                    delay(1000)
                    canNavigate = true
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
            ) {
                item {


                    //notification buttons
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(20.dp),
                    ) {
                        var isChecked by remember { mutableStateOf(false) }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Box(
                                    modifier = Modifier
                                        .height(40.dp)
                                        .aspectRatio(1f)
                                        .clip(CircleShape)
                                        .background(GreenLight)
                                        .align(Alignment.CenterVertically),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_notifications),
                                        contentDescription = "icon",
                                        tint = GreenMain
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(0.7f)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.push_notifications),
                                        fontFamily = INTER_FONT_FAMILY,
                                        fontSize = 14.sp,
                                        lineHeight = 18.sp,
                                        color = Color.Black,
                                    )
                                    Text(
                                        text = stringResource(id = R.string.get_notifications),
                                        fontFamily = INTER_FONT_FAMILY,
                                        fontSize = 10.sp,
                                        lineHeight = 12.sp,
                                        color = Color.Gray,
                                    )
                                }
                            }
                            Switch(
                                checked = isChecked,
                                onCheckedChange = { isChecked = it },
                                thumbContent = {
                                    Box(
                                        modifier = Modifier
                                            .height(8.dp)
                                            .aspectRatio(1f)
                                            .clip(CircleShape)
                                            .background(Color.White)
                                    )
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    uncheckedThumbColor = Color.White,
                                    checkedTrackColor = GreenMain,
                                    uncheckedTrackColor = Color.Gray,
                                    checkedBorderColor = GreenMain,
                                    uncheckedBorderColor = Color.Gray
                                ),

                                modifier = Modifier
                                    .scale(0.8f)
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }

        }
    }
}