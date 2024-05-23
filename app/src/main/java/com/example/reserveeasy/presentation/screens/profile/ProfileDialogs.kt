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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reserveeasy.R
import com.example.reserveeasy.common.Constants.Companion.INTER_FONT_FAMILY
import com.example.reserveeasy.presentation.ui.theme.GreenMain

@Composable
fun ProfileExitDialog(
    onDismiss: () -> Unit,
    onPositiveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.6f)
            .background(Color.Black)
            .clickable {
                onDismiss()
            }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .then(modifier)
            .background(Color.White)
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.are_sure_exit),
                fontFamily = INTER_FONT_FAMILY,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //cancel btn
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(10.dp),
                            color = Color.LightGray
                        )
                        .clickable { onDismiss() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        fontFamily = INTER_FONT_FAMILY,
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        color = Color.Black
                    )
                }

                //exit btn
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(10.dp),
                            color = GreenMain
                        )
                        .clickable { onPositiveClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.prof_exit),
                        fontFamily = INTER_FONT_FAMILY,
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileLanguageDialog(
    onDismiss: (String) -> Unit,
    onPositiveClick: () -> Unit,
    currentLanguage: String,
    modifier: Modifier = Modifier
) {
    var currentLan by remember {
        mutableStateOf(currentLanguage)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.6f)
            .background(Color.Black)
            .clickable {
                onDismiss(currentLan)
            }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .then(modifier)
            .background(Color.White)
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.choose_language),
                fontFamily = INTER_FONT_FAMILY,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(15.dp))

            //kazakh lan
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { currentLan = "kk" },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.kazakh),
                    fontFamily = INTER_FONT_FAMILY,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Image(
                    painter = painterResource(id = if (currentLan == "kk") R.drawable.radio_on else R.drawable.radio_off),
                    contentDescription = "icon",
                    modifier = Modifier.size(22.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .alpha(0.5f)
                    .background(Color.LightGray)
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )

            //russian lan
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { currentLan = "ru" },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.russian),
                    fontFamily = INTER_FONT_FAMILY,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Image(
                    painter = painterResource(id = if (currentLan == "ru") R.drawable.radio_on else R.drawable.radio_off),
                    contentDescription = "icon",
                    modifier = Modifier.size(22.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .alpha(0.5f)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.height(10.dp))

            //english lan
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { currentLan = "en" },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.english),
                    fontFamily = INTER_FONT_FAMILY,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Image(
                    painter = painterResource(id = if (currentLan == "en") R.drawable.radio_on else R.drawable.radio_off),
                    contentDescription = "icon",
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))


        }
    }
}