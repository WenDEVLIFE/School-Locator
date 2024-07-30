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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
    val context = LocalContext.current
    val screenSize = getScreenSize()
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf(0) }


    LaunchedEffect(Unit) {
        while (time > 0) {
            delay(1000L) // 1 second delay
            time--
        }
    }

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

            item {
                Text(
                    text = "Registration",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 30.sp else 40.sp
                )
            }

            item { Spacer(modifier = Modifier.height(26.dp)) }

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
                    textStyle = TextStyle(color = Color.Black, fontSize = if (screenSize == ScreenSize.SMALL) 12.sp else 15.sp),
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
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(5.dp)) }

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

            item {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text(text = "Enter your email") },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .background(Color.Transparent),
                    textStyle = TextStyle(color = Color.Black, fontSize = if (screenSize == ScreenSize.SMALL) 12.sp else 15.sp),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = "Icon",
                            tint = Color.Black
                        )
                    },

                    trailingIcon = {
                        val image = painterResource(id = R.drawable.baseline_send_24)
                        IconButton(onClick = {
                            if (time == 0){
                                time = 60
                                Toast.makeText(
                                    context,
                                    "Email sent",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }else{
                                Toast.makeText(
                                    context,
                                    "Wait for $time seconds",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }) {
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
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(5.dp)) }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    text = if (time == 0) {
                        ""
                    } else {
                        "Resend Email in $time seconds"
                    },
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
                )
            }


            item { Spacer(modifier = Modifier.height(20.dp)) }

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

@Composable
fun loadSignUp() {
    SignUpForm()
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    SchoolLocatorTheme {
        SignUpForm(modifier = Modifier.fillMaxWidth())

    }
}