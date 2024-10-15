package com.example.schoollocator.activity.Screens

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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.R
import com.example.schoollocator.graphs.AppNavigation
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.SchoolLocatorTheme
import com.example.schoollocator.viewmodel.LoginViewModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchoolLocatorTheme {
              AppNavigation(navController = rememberNavController())
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm1(navController: NavHostController, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
   
 // Get the screen size
    val screenSize = getScreenSize()

    // Get the context
    val context = LocalContext.current

    //  Get the view model
    val viewModel: LoginViewModel = viewModel()


    // launched the  effect
    LaunchedEffect(viewModel.isSuccess.value) {
        if (viewModel.isSuccess.value) {
            navController.navigate("Map")
            viewModel.isSuccess.value = false
        }
    }

    // Box annd all the components text, columns, button and text Field
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Green1)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Icon",
                modifier = Modifier.size(
                    width = if (screenSize == ScreenSize.SMALL) 150.dp else 204.dp,
                    height = if (screenSize == ScreenSize.SMALL) 150.dp else 204.dp
                )
            )

            Spacer(modifier = Modifier.height(26.dp))

            Text(
                text = "Login",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = Color.White,
                fontSize = if (screenSize == ScreenSize.SMALL) 30.sp else 40.sp
            )

            Spacer(modifier = Modifier.height(26.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Username",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = Color.White,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

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

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Password",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = Color.White,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

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
                    val image = if (viewModel.isPasswordVisible.value)
                        painterResource(id = R.drawable.see)
                    else
                        painterResource(id = R.drawable.eye)

                    IconButton(onClick = { viewModel.passwordToogle() }) {
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
                visualTransformation = if (viewModel.isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (viewModel.username.value.isNotEmpty() && viewModel.password.value.isNotEmpty()) {
                        viewModel.isSuccess.value = true
                        viewModel.LoginDB()

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
                    text = "Login",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Green1,
                    fontSize = if (screenSize == ScreenSize.SMALL) 20.sp else 25.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
                onClick = {
                    navController.navigate("signUp")
                }
            ) {
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



@Preview(showBackground = true)
@Composable
fun LoginForm1Preview() {
    LoginForm1(navController = rememberNavController())
}