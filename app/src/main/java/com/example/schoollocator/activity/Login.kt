package com.example.schoollocator.activity

import android.os.Bundle
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schoollocator.enum.ScreenSize
import com.example.schoollocator.enum.getScreenSize
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.SchoolLocatorTheme

// This is used for screenn resizing
class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchoolLocatorTheme {
                LoadLogin()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm1(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val screenSize = getScreenSize()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    // This is for the password text field
    var passwordVisible by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Green1) // Apply the Green1 color here
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {

            // Image
            Image(
                painter = painterResource(id = R.drawable.school_locator),
                contentDescription = "Icon",
                modifier = Modifier.size(
                    width = if (screenSize == ScreenSize.SMALL) 150.dp else 204.dp,
                    height = if (screenSize == ScreenSize.SMALL) 150.dp else 204.dp
                )// Adjust the size as needed
            )

            Spacer(modifier = Modifier.height(26.dp))

            // Login text view
            Text(
                text = "Login",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = Color.White,
                fontSize = if (screenSize == ScreenSize.SMALL) 30.sp else 40.sp
            )

            Spacer(modifier = Modifier.height(26.dp))

            // Username text view
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "Username",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = Color.White,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

            // This is for the username text field
            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = {
                    Text(text = "Enter your username")
                },
                shape = RoundedCornerShape(20.dp), // Adjust the corner radius as needed
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Alignment.Start)
                    .background(Color.Transparent),
                textStyle = TextStyle(color = Color.Black,   fontSize = if (screenSize == ScreenSize.SMALL) 12.sp else 15.sp),
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

            Spacer(modifier = Modifier.height(5.dp))

            // Password text view
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "Password",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = Color.White,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

            // This is for the password text field
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(text = "Enter your password")
                },
                shape = RoundedCornerShape(20.dp), // Adjust the corner radius as needed
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Alignment.Start)
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

                // added a trailing icon to toggle password visibility
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

                // Use visualTransformation to hide the password
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White, // background color
                    focusedIndicatorColor = Color.Transparent, // focused border color
                    unfocusedIndicatorColor = Color.Transparent // unfocused border color
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Login Button
            Button(
                onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(20.dp), // Adjust the corner radius as needed
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)

            ) {
                Text(
                    text = "Login",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Green1,
                    fontSize = if (screenSize == ScreenSize.SMALL) 20.sp else 25.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Added text button for sign up
            TextButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    onClick()
                }
            ){
                Text(
                    text = "Don't have an acccount? click me to sign up",
                    fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 12.sp else 15.sp
                )
            }
        }
    }
}

@Composable
fun LoadLogin() {
    LoginForm1()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SchoolLocatorTheme {
        LoginForm1(modifier = Modifier.fillMaxWidth())
    }
}