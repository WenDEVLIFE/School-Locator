package com.example.schoollocator.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schoollocator.R
import com.example.schoollocator.activity.ui.theme.SchoolLocatorTheme
import com.example.schoollocator.ui.theme.Green1
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
fun SignUpForm(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {

    // get the conntext
    val context = LocalContext.current

    // get the screen size  and set the variables
    val screenSize = getScreenSize()

    // set the variables for the text fields
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var showOTP by remember { mutableStateOf(false) }


    if (showOTP) {
        loadOTP()
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
                        value = username,
                        onValueChange = { username = it },
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
                        value = password,
                        onValueChange = { password = it },
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
                            val image = if (passwordVisible)
                                painterResource(id = R.drawable.see)
                            else
                                painterResource(id = R.drawable.eye)

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
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
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                        value = email,
                        onValueChange = { email = it },
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
                            showOTP = true
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
    otpLength: Int = 6,
    onOtpComplete: (String) -> Unit
) {

    // remember is used to store the state of the otp values
    var otpValues by remember { mutableStateOf(List(otpLength) { "" }) }

    // Row is used to display the otp text fields in a row
    Row {
        otpValues.forEachIndexed { index, value ->
            TextField(
                value = value,

                // onValueChange is called when the value of the TextField changes
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        otpValues = otpValues.toMutableList().apply { this[index] = newValue }

                        // Move focus to the previous TextField
                        if (newValue.isNotEmpty() && index < otpLength - 1) {
                            // Move focus to the next TextField
                        }

                        // Call the onOtpComplete function when all the otp values are entered
                        if (otpValues.all { it.isNotEmpty() }) {
                            onOtpComplete(otpValues.joinToString(""))
                        }
                    }
                },

                // placeholder is used to display the hint text in the TextField
                placeholder = { Text(text = "Enter your OTP") },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .size(45.dp)
                    .padding(4.dp),
                textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
    SignUpForm()
}

@Composable
fun loadOTP() {
    LoadOTP()
}

@Composable
fun LoadOTP(modifier: Modifier = Modifier,onClick: () -> Unit = {}) {
    // get the conntext
    val context = LocalContext.current

    // This will get the screen size
    val screenSize = getScreenSize()

    var time by remember { mutableStateOf(60) }

    // This will launch the timer
    LaunchedEffect(time) {
        while (time > 0) {
            delay(1000L) // 1 second delay
            time--
        }
    }

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

            item { Spacer(modifier = Modifier.height(26.dp)) }

            // This is for OTP title
            item {
                Text(
                    text = "Email OTP Verification",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 30.sp else 40.sp
                )
            }

            item { Spacer(modifier = Modifier.height(26.dp)) }

            // text
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    text = "Enter your one time pin",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
                )
            }
            item { Spacer(modifier = Modifier.height(5.dp)) }

            // otp text field
            item {
                OTPTextField(otpLength = 6, onOtpComplete = {})
            }

            // This is for OTP title
            item {
                Text(
                    text = "Time remaining : $time",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
                )
            }

            // This is for the button registration
            item {
                Button(
                    onClick = {
                        if (time == 0) {
                            time = 60
                            Toast.makeText(context, "Code Resend Successfully", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(context, "Please wait for the time to elapse", Toast.LENGTH_SHORT).show()
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
                    onClick = { onClick() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                ) {
                    Text(
                        text = "Confim OTP",
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        color = Green1,
                        fontSize = if (screenSize == ScreenSize.SMALL) 20.sp else 25.sp
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
        SignUpForm(modifier = Modifier.fillMaxWidth())

    }
}

@Preview
@Composable
fun OTPPreview() {
    SchoolLocatorTheme {
        OTPTextField( otpLength = 6, onOtpComplete = {})
    }
}

@Preview
@Composable
fun GreetingPreview4() {
    SchoolLocatorTheme {
        LoadOTP(modifier = Modifier.fillMaxWidth())
    }
}
