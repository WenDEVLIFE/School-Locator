package com.example.schoollocator.activity.screens

import SendEmail
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.schoollocator.R
import com.example.schoollocator.components.AlertDialog
import com.example.schoollocator.components.OTPTextField
import com.example.schoollocator.database.InsertUserScreen
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.darkblue
import com.example.schoollocator.viewmodel.OTPViewModel
import com.example.schoollocator.viewmodel.SignUpModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun LoadOTP(
    navController: NavController,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    jsonMapString: String // Receive the JSON string
) {
    val map: Map<String, String> = Json.decodeFromString(jsonMapString)
    val username = map["username"]
    val email = map["email"]
    val password = map["password"]

    println("Username: $username")
    println("Email: $email")
    println("Password: $password")

    val code = remember { mutableStateOf(GenerateCode()) }
    val viewModel1: SignUpModel = viewModel()
    val context = LocalContext.current
    val screenSize = getScreenSize()
    val viewModel: OTPViewModel = viewModel()

    var emailSentSuccess by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showInsertUserScreen by remember { mutableStateOf(false) }
    var enteredOtp by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.startTimer()
        CoroutineScope(Dispatchers.IO).launch {
            if (email != null) {
                SendEmail(context, email, code.value) { success ->
                    emailSentSuccess = success
                    showDialog = true
                }
            }
        }
    }

    BackHandler {
        try {
            Toast.makeText(context, "Back button pressed", Toast.LENGTH_SHORT).show()
            viewModel.setBackPressed3(true)
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(viewModel.showSuccess.value) {
        if (viewModel.showSuccess.value) {
            navController.navigate("success")
        }
    }

    LaunchedEffect(viewModel.isBackPressed3.value) {
        if (viewModel.isBackPressed3.value) {
            navController.popBackStack()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(darkblue)
            .padding(16.dp)
    ) {
        SegmentedStepProgressBar(totalSteps = 3, currentStep = 2, modifier = Modifier.fillMaxWidth())
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item {
                Text(
                    text = "Email OTP Verification",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 30.sp else 40.sp
                )
            }
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

            item {
                OTPTextField(otpLength = 4, onOtpComplete = { otp ->
                    enteredOtp = otp
                })
            }

            item {
                Text(
                    text = "Time remaining :${viewModel.time.value}",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Color.White,
                    fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
                )
            }

            item {
                Button(
                    onClick = {
                        if (viewModel.time.value == 0) {
                            viewModel.startTimer()
                            CoroutineScope(Dispatchers.IO).launch {
                                if (email != null) {
                                    SendEmail(context, email, code.value) { success ->
                                        emailSentSuccess = success
                                        showDialog = true
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(context, "Please wait for the timer to finish", Toast.LENGTH_SHORT).show()
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

            item {
                Button(
                    onClick = {
                        if (enteredOtp == code.value) {
                            showInsertUserScreen = true
                        } else {
                            Toast.makeText(context, "Invalid OTP", Toast.LENGTH_SHORT).show()
                        }
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

    if (showDialog) {
        if (emailSentSuccess) {
            AlertDialog("Email Alert!", "Email sent successfully", dialogState = remember { mutableStateOf(true) })
        } else {
            Toast.makeText(context, "Failed to send email", Toast.LENGTH_SHORT).show()
        }
    }

    if (showInsertUserScreen) {
        val userData = mapOf(
            "username" to username,
            "email" to email,
            "password" to password
        )
        InsertUserScreen(userData)

        AlertDialog(
            title = "Registration",
            message = "User registered successfully",
            dialogState = remember { mutableStateOf(true) }
        ) {
            // Navigate to the success screen after the dialog is dismissed
            navController.navigate("success")
        }

    }
}

fun GenerateCode(): String {
    val codeLength = 4
    val allowedChars = ('0'..'9')
    return (1..codeLength)
        .map { allowedChars.random() }
        .joinToString("")
}