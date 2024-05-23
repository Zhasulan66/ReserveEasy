package com.example.reserveeasy.presentation.screens.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.reserveeasy.R
import com.example.reserveeasy.common.Constants
import com.example.reserveeasy.domain.model.User
import com.example.reserveeasy.presentation.LanguageManager
import com.example.reserveeasy.presentation.navigation.NavigationView
import com.example.reserveeasy.presentation.navigation.Screen
import com.example.reserveeasy.presentation.viewmodel.MainViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    languageManager: LanguageManager
) {
    val viewModel = hiltViewModel<MainViewModel>()
    //val profileUserState by viewModel.profileUserState.collectAsState()

    var showExitDialog by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            /*when (profileUserState) {
                is Resource.Loading -> {
                    LoadingScreen()
                }

                is Resource.Error -> {
                    ErrorScreen(modifier = Modifier, retryAction = {
                        navController.navigate(
                            route = navController.currentDestination?.route ?: ""
                        ) {
                            popUpTo(
                                navController.previousBackStackEntry?.destination?.id
                                    ?: return@navigate
                            )
                        }
                    })
                }

                is Resource.Success -> {
                    val user =
                        (profileUserState as Resource.Success<User>).data
                    ProfileScreenSuccess(
                        user = user,
                        navController = navController,
                    )

                }

                is Resource.Initial -> {
                    viewModel.fetchUser()
                }
            }*/

            item {
                ProfileScreenSuccess(
                    user = User(
                        id = "1",
                        email = "some@gmail.com",
                        password = "1234",
                        isStaff = false,
                        isSuperAdmin = false,
                        createdAt = "23.05.2024",
                        updatedAt = "23.05.2024"
                    ),
                    navController = navController,
                    onExitDialogClick = {
                        showExitDialog = it
                    },
                    onLanguageDialogClick = {
                        showLanguageDialog = it
                    },
                )
            }

        }



        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.ProfileScreen.route) {
                        inclusive = true
                    }
                }
            },
            onBookingClick = {},
            onFavouritesClick = {
                navController.navigate(Screen.FavouriteScreen.route) {
                    popUpTo(Screen.ProfileScreen.route) {
                        inclusive = true
                    }
                }
            },
            onProfileClick = {
                navController.navigate(Screen.ProfileScreen.route) {
                    popUpTo(Screen.ProfileScreen.route) {
                        inclusive = true
                    }
                }
            },
            btnId = 4,
        )

        //exit dialog
        if(showExitDialog){
            ProfileExitDialog(
                onDismiss = { showExitDialog = false },
                onPositiveClick = {
                    viewModel.deleteUserId()
                    navController.navigate(Screen.LoginScreen.route){
                        popUpTo(Screen.ProfileScreen.route){
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }

        //language dialog
        if(showLanguageDialog){
            ProfileLanguageDialog(
                onDismiss = { language ->
                    showLanguageDialog = false
                    if(languageManager.getCurrentLanguage() != language){
                        languageManager.setLanguage(language)
                        languageManager.restartActivity()
                    }

                },
                onPositiveClick = {},
                currentLanguage = languageManager.getCurrentLanguage(),
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }

    }
}

@Composable
fun ProfileScreenSuccess(
    user: User,
    navController: NavController,
    onExitDialogClick: (Boolean) -> Unit,
    onLanguageDialogClick: (Boolean) -> Unit,
) {
    val context = LocalContext.current
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = stringResource(id = R.string.my_account),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Constants.INTER_FONT_FAMILY,
        color = Color.Black,
    )
    Spacer(modifier = Modifier.height(20.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //profile img
            Image(
                painter = painterResource(id = R.drawable.profile_img),
                contentDescription = "img"
            )
            Spacer(modifier = Modifier.width(4.dp))

            //user name & city
            Column {
                Text(
                    text = "William Shakespeare",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Black,
                    maxLines = 2
                )
                Text(
                    text = "Almaty",
                    fontSize = 12.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Gray,
                )
            }
        }

        //edit btn
        Image(
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = "icon",
            modifier = Modifier
                .clickable {  }
        )
    }
    Spacer(modifier = Modifier.height(20.dp))

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
        //Notifications
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_notifications),
                    contentDescription = "icon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.notifications),
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
                        navController.navigate(Screen.NotificationSettingScreen.route)
                    }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        //Promo code
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_promo_code),
                    contentDescription = "icon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.enter_promo_code),
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
                    .clickable {}
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        //Invite friends
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_invite),
                    contentDescription = "icon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.invite_friend),
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
                        val invitationLink = "https://example.com/invite"
                        val intent =  Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("https://api.whatsapp.com/send?text=$invitationLink")
                        }
                        context.startActivity(intent)
                    }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        //Language
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_language),
                    contentDescription = "icon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.language),
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
                        onLanguageDialogClick(true)
                    }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
    Spacer(modifier = Modifier.height(20.dp))

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
        //Support
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_support),
                    contentDescription = "icon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.support),
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
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:") // only email apps should handle this
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("support8888@gmail.kz"))
                            putExtra(Intent.EXTRA_SUBJECT, "App support")
                        }
                        context.startActivity(intent)
                    }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        //Exit
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.baseline_logout_24),
                    contentDescription = "icon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.logout),
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
                        onExitDialogClick(true)
                    }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }


}