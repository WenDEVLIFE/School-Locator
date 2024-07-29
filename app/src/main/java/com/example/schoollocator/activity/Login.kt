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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.SchoolLocatorTheme

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

@Composable
fun LoginForm1(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
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
                modifier = Modifier.size(204.dp) // Adjust the size as needed
            )

            Spacer(modifier = Modifier.height(26.dp))

            // Login text view
            Text(
                text = "Login",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = Color.White,
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.height(26.dp))

            // Username text view
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "Username",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = Color.White,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
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

                // Added icons on the textfield
                leadingIcon = {
                    Icon(modifier = Modifier.padding(end = 10.dp),
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = "Icon",
                        tint = Color.Black
                        )
                },

                // added text style
                textStyle =  TextStyle(color =Color.Black, fontSize = 20.sp),


            )
        }
    }
}

@Composable
fun LoadLogin(){
    LoginForm1()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SchoolLocatorTheme {
        LoginForm1(modifier = Modifier.fillMaxWidth())
    }
}
