package com.example.reserveeasy.presentation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.reserveeasy.R
import com.example.reserveeasy.common.Constants
import com.example.reserveeasy.domain.model.LoginResponse
import com.example.reserveeasy.domain.model.Resource
import com.example.reserveeasy.domain.model.User
import com.example.reserveeasy.domain.model.UserRequest
import com.example.reserveeasy.presentation.navigation.Screen
import com.example.reserveeasy.presentation.screens.ErrorScreen
import com.example.reserveeasy.presentation.screens.LoadingScreen
import com.example.reserveeasy.presentation.ui.theme.BlueMain
import com.example.reserveeasy.presentation.ui.theme.GreenMain
import com.example.reserveeasy.presentation.viewmodel.MainViewModel


@Composable
fun RegistrationScreen(
    navController: NavController,
) {

    val viewModel = hiltViewModel<MainViewModel>()
    val registrationState by viewModel.registrationState.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()){

        when(registrationState){
            is Resource.Initial -> {
                RegisterFields(navController, viewModel)
            }
            is Resource.Loading -> {
                LoadingScreen()
            }
            is Resource.Error -> {
                ErrorScreen(modifier = Modifier, retryAction = {
                    navController.navigate(route = navController.currentDestination?.route ?: ""){
                        popUpTo(navController.previousBackStackEntry?.destination?.id ?: return@navigate)
                    }
                })
            }
            is Resource.Success -> {
                val userResponse = (registrationState as Resource.Success<User>).data
                Toast.makeText(LocalContext.current, "Registered Successfully!", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.LoginScreen.route){
                    popUpTo(Screen.RegistrationScreen.route){ inclusive = true }
                }
                viewModel.registrationSuccess()

            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterFields(
    navController: NavController,
    viewModel: MainViewModel
){
    var userName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var userPhone by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var userPassword2 by remember { mutableStateOf("") }
    var isPasswordHide by remember { mutableStateOf(true) }
    var isPasswordHide2 by remember { mutableStateOf(true) }

    var isUserNameIncorrect by remember { mutableStateOf(false) }
    var isUserEmailIncorrect by remember { mutableStateOf(false) }
    var isUserPhoneIncorrect by remember { mutableStateOf(false) }
    var isUserPasswordIncorrect by remember { mutableStateOf(false) }
    var isUserPassword2Incorrect by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Sign Up",
                    fontSize = 22.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(20.dp))

                //username
                TextField(
                    value = userName,
                    onValueChange = { text ->
                        userName = text
                    },
                    textStyle = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if(isUserNameIncorrect) Color.Red else Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    placeholder = {
                        Text(
                            text = "Name Surname",
                            fontSize = 14.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            color = Color.Gray,
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))

                //email
                TextField(
                    value = userEmail,
                    onValueChange = { text ->
                        userEmail = text
                    },
                    textStyle = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if(isUserEmailIncorrect) Color.Red else Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    placeholder = {
                        Text(
                            text = "Email",
                            fontSize = 14.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            color = Color.Gray,
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))

                //password
                TextField(
                    value = userPassword,
                    onValueChange = { text ->
                        userPassword = text
                    },
                    textStyle = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if(isUserPasswordIncorrect) Color.Red else Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    placeholder = {
                        Text(
                            text = "Password",
                            fontSize = 14.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            color = Color.Gray,
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (isPasswordHide) PasswordVisualTransformation()
                    else VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            painter = if (isPasswordHide) painterResource(id = R.drawable.baseline_visibility_off_24)
                            else painterResource(id = R.drawable.baseline_visibility_24),
                            contentDescription = "",
                            tint = Color.Gray,
                            modifier = Modifier.clickable {
                                isPasswordHide = !isPasswordHide
                            })
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))

                //password2
                TextField(
                    value = userPassword2,
                    onValueChange = { text ->
                        userPassword2 = text
                    },
                    textStyle = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if(isUserPassword2Incorrect) Color.Red else Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    placeholder = {
                        Text(
                            text = "Confirm password",
                            fontSize = 14.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            color = Color.Gray,
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (isPasswordHide2) PasswordVisualTransformation()
                    else VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            painter = if (isPasswordHide2) painterResource(id = R.drawable.baseline_visibility_off_24)
                            else painterResource(id = R.drawable.baseline_visibility_24),
                            contentDescription = "",
                            tint = Color.Gray,
                            modifier = Modifier.clickable {
                                isPasswordHide2 = !isPasswordHide2
                            })
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))

                //Register btn
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            color = GreenMain
                        )
                        .clickable {
                            isUserNameIncorrect = userName.isEmpty()
                            isUserEmailIncorrect = userEmail.isEmpty()
                            isUserPasswordIncorrect = userPassword.isEmpty()
                            if (!isValidEmail(userEmail)) {
                                isUserEmailIncorrect = true
                            }
                            if (!isPasswordValid(userPassword)) {
                                isUserPasswordIncorrect = true
                            }
                            if (!isUserNameIncorrect && !isUserEmailIncorrect &&
                                !isUserPhoneIncorrect && !isUserPasswordIncorrect
                            ) {
                                val userRequest = UserRequest(userEmail, userPassword)
                                viewModel.registerUser(userRequest)
                            }

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                //dont have an account
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Already have an account?",
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Login",
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = BlueMain,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.LoginScreen.route){
                                    popUpTo(Screen.RegistrationScreen.route){ inclusive = true }
                                }
                            }

                    )
                }


            }
        }
    }
}

// Function to validate email
internal fun isValidEmail(email: String): Boolean {
    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    return email.matches(emailPattern)
}

fun isPasswordValid(password: String): Boolean {
    // Check minimum length
    if (password.length < 8) {
        return false
    }

    // Check if password contains numbers
    val containsNumber = password.any { it.isDigit() }
    if (!containsNumber) {
        return false
    }

    // Check if password contains at least one uppercase letter
    val containsUppercase = password.any { it.isUpperCase() }
    if (!containsUppercase) {
        return false
    }

    // Check if password is entirely numeric
    if (password.all { it.isDigit() }) {
        return false
    }

    // Define common patterns to avoid
    val commonPatterns = listOf("password", "qwerty", "abc", "admin")

    // Check if password contains any common patterns
    if (commonPatterns.any { password.contains(it, ignoreCase = true) }) {
        return false
    }

    // If all criteria pass, return true
    return true
}