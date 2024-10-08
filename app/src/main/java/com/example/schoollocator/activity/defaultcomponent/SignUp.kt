package com.example.schoollocator.activity.defaultcomponent

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.SchoolLocatorTheme
import com.example.schoollocator.viewmodel.SignUpModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp


class SignUp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchoolLocatorTheme {
                AppNavigation()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpForm(navController: NavController, modifier: Modifier, onClick: () -> Unit = {}) {

    // Added the view model
    val viewModel: SignUpModel = viewModel()

    // get the conntext
    val context = LocalContext.current

    // get the screen size  and set the variables
    val screenSize = getScreenSize()


    // This is for the back press
    BackHandler {
        context.startActivity(Intent(context, Login::class.java))
        (context as? ComponentActivity)?.finish()
    }

    // This are the  box and lazy column that will compose the UI
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Green1)
            .padding(16.dp)

    ) {
        SegmentedStepProgressBar(totalSteps = 3, currentStep = 1, modifier = Modifier.fillMaxWidth())
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp),
            ) {
            item{
                Spacer(modifier = Modifier.height(20.dp))
            }

            // This is for the image
            item {
                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Icon",
                    modifier = Modifier.size(
                        width = if (screenSize == ScreenSize.SMALL) 150.dp else 204.dp,
                        height = if (screenSize == ScreenSize.SMALL) 150.dp else 204.dp
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(26.dp)) }

            // This is for the registration text
            item {
                Text(
                    text = "Registration",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 30.sp else 40.sp
                )
            }

            item { Spacer(modifier = Modifier.height(26.dp)) }

            // This is for the username text
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    text = "Username",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
                )
            }

            item { Spacer(modifier = Modifier.height(5.dp)) }

            // This is for the username text field
            item {
                TextField(
                    value = viewModel.username.value,
                    onValueChange = { viewModel.setUsername(it) },
                    placeholder = { Text(text = "Enter your username") },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .background(Color.Transparent),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = if (screenSize == ScreenSize.SMALL) 12.sp else 15.sp
                    ),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            painter = painterResource(id = R.drawable.baseline_person_24),
                            contentDescription = "Icon",
                            tint = Color.Black
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(5.dp)) }


            // This is for the password text
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    text = "Password",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
                )
            }


            item { Spacer(modifier = Modifier.height(5.dp)) }

            // This is for the password text field
            item {
                TextField(
                    value = viewModel.password.value,
                    onValueChange = { viewModel.setPassword(it) },
                    placeholder = { Text(text = "Enter your password") },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .background(Color.Transparent),
                    textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            painter = painterResource(id = R.drawable.baseline_key_24),
                            contentDescription = "Icon",
                            tint = Color.Black
                        )
                    },
                    trailingIcon = {

                        // This will check if the password is visible or not
                        val image = if (viewModel.isPasswordVisible.value)
                            painterResource(id = R.drawable.see)
                        else
                            painterResource(id = R.drawable.eye)

                        IconButton(onClick = { viewModel.togglePasswordVisible() }) {
                            Icon(
                                painter = image,
                                contentDescription = "Toggle password visibility",
                                modifier = Modifier.size(
                                    width = if (screenSize == ScreenSize.SMALL) 20.dp else 24.dp,
                                    height = if (screenSize == ScreenSize.SMALL) 20.dp else 24.dp
                                )
                            )
                        }
                    },

                    // This is to hide the password and unnhide the password
                    visualTransformation = if (viewModel.isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(5.dp)) }


            // This is for the email text
            item {
                Text(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    text = "Email",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
                )
            }

            // This is for the email text field where the user willl type the valid email
            item {
                TextField(
                    value = viewModel.email.value,
                    onValueChange = { viewModel.setEmail(it) },
                    placeholder = { Text(text = "Enter your email") },

                    // added rounded shape
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .background(Color.Transparent),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = if (screenSize == ScreenSize.SMALL) 12.sp else 15.sp
                    ),

                    // This is to add the icon to the text field
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = "Icon",
                            tint = Color.Black
                        )
                    },

                    // This is for the colors of the text field
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            // This is for the button registration
            item {
                Button(
                    onClick = {
                        if (viewModel.username.value.isNotEmpty() && viewModel.email.value.isNotEmpty() && viewModel.password.value.isNotEmpty()) {
                            navController.navigate("otp")
                        } else {
                            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                ) {
                    Text(
                        text = "Register",
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        color = Green1,
                        fontSize = if (screenSize == ScreenSize.SMALL) 20.sp else 25.sp
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(10.dp)) }

        }
    }
}

// Otp text field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPTextField(
    otpLength: Int = 4,
    onOtpComplete: (String) -> Unit
) {

    val context = LocalContext.current

    // remember is used to store the state of the otp values
    var otpValues by remember { mutableStateOf(List(otpLength) { "" }) }
    val focusRequesters = List(otpLength) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    // Row is used to display the otp text fields in a row
    Row {
        otpValues.forEachIndexed { index, value ->
            TextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        otpValues = otpValues.toMutableList().apply { this[index] = newValue }

                        // Move focus to the next TextField
                        if (newValue.isNotEmpty() && index < otpLength - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }

                        // Call the onOtpComplete function when all the otp values are entered
                        if (otpValues.all { it.isNotEmpty() }) {
                            onOtpComplete(otpValues.joinToString(""))
                        }
                    }
                },
                placeholder = { Text(text = "") },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .size(60.dp)
                    .padding(4.dp)
                    .focusRequester(focusRequesters[index]),
                textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (index == otpLength - 1) {
                            focusManager.clearFocus()
                        }
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {

    // This is for the nav controller
    val navController = rememberNavController()

    // use navhost to navigate between the composable functions
    NavHost(navController = navController, startDestination = "signUp") {

        // added the composable functions
        composable("signUp") {
            SignUpForm(navController = navController, modifier = Modifier.fillMaxSize())
        }

        // added the composable functions
        composable("otp") {
            LoadOTP(navController = navController, modifier = Modifier.fillMaxSize())
        }

        // added the composable functions
        composable("success") {
            LoadSuccess(navController = navController,modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun SegmentedStepProgressBar(
    totalSteps: Int,
    currentStep: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = Color.Gray
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (step in 1..totalSteps) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    // Draw the background color
                    drawRect(
                        color = if (step <= currentStep) Color.Green else Color.White,
                        size = size
                    )

                    // Draw the rounded rectangle
                    drawRoundRect(
                        color = Color.White,
                        size = size,
                        cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()),
                        style = Stroke(width = 4.dp.toPx())
                    )
                }
            }
        }
    }
}

// This below here  are the previews for the composable functions
@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    SchoolLocatorTheme {
        SignUpForm(navController = rememberNavController(), modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
fun OTPPreview() {
    SchoolLocatorTheme {
        OTPTextField( otpLength = 4, onOtpComplete = {})
    }
}

@Preview
@Composable
fun GreetingPreview4() {
    SchoolLocatorTheme {
        LoadOTP(navController = rememberNavController(), modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
fun GreetingPreview5() {
    SchoolLocatorTheme {
        LoadSuccess(navController = rememberNavController(),modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
fun GreetingPreview6() {
   SegmentedStepProgressBar(totalSteps = 3, currentStep = 1, modifier = Modifier.fillMaxWidth())
}