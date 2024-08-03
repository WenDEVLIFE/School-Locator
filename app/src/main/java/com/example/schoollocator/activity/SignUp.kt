package com.example.schoollocator.activity

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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.schoollocator.R
import com.example.schoollocator.activity.ui.theme.SchoolLocatorTheme
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.viewmodel.SignUpModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize
import kotlinx.coroutines.delay

class SignUp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchoolLocatorTheme {
                loadSignUp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpForm(username1: String, email1: String, password1: String,modifier: Modifier = Modifier, onClick: () -> Unit = {}) {

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


    if (viewModel.showOTP.value) {
        loadOTP(viewModel.username.value, viewModel.email.value, viewModel.password.value)
    } else {
        // This are the  box and lazy column that will compose the UI
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Green1)
                .padding(16.dp)
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {

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
                                viewModel.setShowOTP(true)
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
@Composable
fun loadSignUp() {
    SignUpForm(username1 = "", email1 = "", password1 = "", modifier = Modifier.fillMaxWidth())
}

@Composable
fun loadOTP(username: String, email: String, password: String) {
    LoadOTP(username = username, email = email, password = password)
}

@Composable
fun LoadOTP(
    username: String,
    email: String,
    password: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    // get the conntext
    val context = LocalContext.current

    // This will get the screen size
    val screenSize = getScreenSize()

    // This is for the view model
    val viewModel: SignUpModel = viewModel()

    // Start the timer when the composable is first displayed
    LaunchedEffect(Unit) {
        viewModel.startTimer()
    }

    // Handle back press
    BackHandler {
        // Define the action to be taken on back press
        Toast.makeText(context, "Back button pressed", Toast.LENGTH_SHORT).show()
        viewModel.setBackPressed3(true)

    }


    if (viewModel.showSuccess.value) {
        // Load the success screen
        LoadSuccess()
        viewModel.setShowSuccess(true)
    }

    else if (viewModel.isBackPressed3.value) {
        // Go back to the previous screen
        SignUpForm(
            username1 = username,
            email1 = email,
            password1 = password,
            modifier = Modifier.fillMaxWidth()
        )
        viewModel.setBackPressed3(true)
    }
    else {
        // Box and LazyColumn together with the items compose the UI
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Green1)
                .padding(16.dp)
        ) {

            // LazyColumn is used to display the items in a vertical list
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                // This is for OTP title
                item {
                    Text(
                        text = "Email OTP Verification",
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        color = Color.White,
                        fontSize = if (screenSize == ScreenSize.SMALL) 30.sp else 40.sp
                    )
                }
                // This is for the image
                item {
                    Image(
                        painter = painterResource(id = R.drawable.otp),
                        contentDescription = "Icon",
                        modifier = Modifier.size(
                            width = if (screenSize == ScreenSize.SMALL) 150.dp else 204.dp,
                            height = if (screenSize == ScreenSize.SMALL) 150.dp else 204.dp
                        )
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                // text
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                        text = "We have sent a verification code to your email address",
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        color = Color.White,
                        fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
                    )
                }
                item { Spacer(modifier = Modifier.height(15.dp)) }

                // otp text field
                item {
                    OTPTextField(otpLength = 4, onOtpComplete = {})
                }

                // This is for OTP title
                item {
                    Text(
                        text = "Time remaining : ${viewModel.time.value}",
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        color = Color.White,
                        fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
                    )
                }

                // This is for the button registration
                item {
                    Button(
                        onClick = {
                            if (viewModel.time.value == 0) {
                                viewModel.startTimer()
                                Toast.makeText(
                                    context,
                                    "Code Resend Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please wait for the time to elapse",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Text(
                            text = "Resend the code",
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                            color = Green1,
                            fontSize = if (screenSize == ScreenSize.SMALL) 20.sp else 25.sp
                        )
                    }
                }

                // This is for the button registration
                item {
                    Button(
                        onClick = {

                            // set the boolean to true to show the success screen
                            viewModel.setShowSuccess(true)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Text(
                            text = "Submit",
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                            color = Green1,
                            fontSize = if (screenSize == ScreenSize.SMALL) 20.sp else 25.sp
                        )
                    }
                }
            }
        }
    }
}

// This method wil load to success screen
@Composable
fun LoadSuccess(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {

    // This is for the context
    val context = LocalContext.current

    // This is for the view model
    val viewModel: SignUpModel = viewModel()

    // get the screenn size
    val screenSize = getScreenSize()

    // TODO: Fix the bug on the state and host here in the load screen
    BackHandler {
        context.startActivity(Intent(context, Login::class.java))
        (context as? ComponentActivity)?.finish()
        viewModel.setBackPressed2(true)
    }

    // This will check if the back is pressed
    if (viewModel.isBackPressed1.value) {
        SignUpForm(username1 = "", email1 = "", password1 = "", modifier = Modifier.fillMaxWidth())
        viewModel.setBackPressed1(true)

    }
    
    // This will check if the back is pressed
    else if (viewModel.isBackPressed2.value) {
        context.startActivity(Intent(context, Login::class.java))
        (context as? ComponentActivity)?.finish()
        viewModel.setBackPressed3(true)
    }
    else {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Green1)
                .padding(16.dp),
            contentAlignment = Alignment.Center

        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp),
            ) {
                item {
                    Text(
                        text = "Registration Successful",
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        color = Color.White,
                        fontSize = if (screenSize == ScreenSize.SMALL) 30.sp else 35.sp
                    )
                }

                item { Spacer(modifier = Modifier.height(20.dp)) }


                // This is an succcess icon image
                item {
                    Image(
                        painter = painterResource(id = R.drawable.success),
                        contentDescription = "Icon",
                        modifier = Modifier.size(
                            width = if (screenSize == ScreenSize.SMALL) 120.dp else 150.dp,
                            height = if (screenSize == ScreenSize.SMALL) 120.dp else 150.dp
                        )
                    )
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }

                // text
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "You have successfully registered to School Locator",
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        color = Color.White,
                        fontSize = if (screenSize == ScreenSize.SMALL) 25.sp else 30.sp
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                // This is for the button registration
                item {
                    Button(
                        onClick = {

                            // set the boolean to true to show the success screen
                            viewModel.setBackPressed1(true)


                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Text(
                            text = "Create a new one",
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                            color = Green1,
                            fontSize = if (screenSize == ScreenSize.SMALL) 20.sp else 25.sp
                        )
                    }
                }

                item {
                    Button(
                        onClick = {
                            // This will go back to the previous screen
                            viewModel.setBackPressed1(true)

                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Text(
                            text = "Go to login",
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                            color = Green1,
                            fontSize = if (screenSize == ScreenSize.SMALL) 20.sp else 25.sp
                        )
                    }


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
        SignUpForm(username1 = "", email1 = "", password1 = "", modifier = Modifier.fillMaxWidth())

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
        LoadOTP(username = "username", email = "email", password = "password", modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
fun GreetingPreview5() {
    SchoolLocatorTheme {
        LoadSuccess(modifier = Modifier.fillMaxWidth())
    }
}
