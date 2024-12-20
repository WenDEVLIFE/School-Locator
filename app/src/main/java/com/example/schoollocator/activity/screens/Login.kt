package com.example.schoollocator.activity.screens

import android.app.Application
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.viewmodel.LoginViewModel
import com.example.schoollocator.viewmodel.SessionViewModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm1(navController: NavHostController, sessionViewModel: SessionViewModel, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(sessionViewModel))
    val screenSize = getScreenSize()
    val context = LocalContext.current

    // Observe the isLoggedIn state
    val isLoggedIn by sessionViewModel.isLoggedIn.observeAsState()


    // use launched effect for avoiding skipping frames
    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn == true) {
            val username = sessionViewModel.username.value
            val email = sessionViewModel.email.value
            val role = sessionViewModel.role.value

            val map = hashMapOf(
                "username" to username,
                "email" to email,
                "role" to role
            )

            // Serialize map to JSON string
            val jsonString = Json.encodeToString(map)

            navController.navigate("Map/$jsonString") {
                popUpTo("Login") {
                    inclusive = true
                }
            }
        }
    }

    if (isLoggedIn != true) {
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
                    value = loginViewModel.username.value,
                    onValueChange = { loginViewModel.setUsername(it) },
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
                    value = loginViewModel.password.value,
                    onValueChange = { loginViewModel.setPassword(it) },
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
                        val image = if (loginViewModel.isPasswordVisible.value)
                            painterResource(id = R.drawable.see)
                        else
                            painterResource(id = R.drawable.eye)

                        IconButton(onClick = { loginViewModel.passwordToogle() }) {
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
                    visualTransformation = if (loginViewModel.isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (loginViewModel.username.value.isNotEmpty() && loginViewModel.password.value.isNotEmpty()) {
                            loginViewModel.isSuccess.value = true
                            loginViewModel.LoginDB(navController, context)
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
}


@Preview(showBackground = true)
@Composable
fun LoginForm1Preview() {
    val context = LocalContext.current
    val sessionViewModel = SessionViewModel(context.applicationContext as Application)
    LoginForm1(navController = rememberNavController(), sessionViewModel = sessionViewModel)
}

class SessionViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SessionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SessionViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class LoginViewModelFactory(private val sessionViewModel: SessionViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(sessionViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

