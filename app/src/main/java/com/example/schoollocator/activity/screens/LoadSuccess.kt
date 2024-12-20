package com.example.schoollocator.activity.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.viewmodel.SuccessViewModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize

// This method wil load to success screen
@Composable
fun LoadSuccess(navController: NavController, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {

    // This is for the context
    val context = LocalContext.current

    // This is for the view model
    val viewModel: SuccessViewModel = viewModel()

    // get the screenn size
    val screenSize = getScreenSize()

    BackHandler {
        navController.navigate("Login")
    }

    // This will check if the back is pressed
    LaunchedEffect(viewModel.isBackPressed1.value) {
        if (viewModel.isBackPressed1.value) {
            viewModel.setBackPressed1(true)
            navController.navigate("signUp")

        }
    }

    // This will check if the back is pressed
    LaunchedEffect(viewModel.isBackPressed2.value) {
        if (viewModel.isBackPressed2.value) {
          navController.navigate("Login")
            viewModel.setBackPressed2(true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green1)
            .padding(16.dp),

    )
    {
        Spacer(modifier = Modifier.height(40.dp))
        SegmentedStepProgressBar(totalSteps = 3, currentStep = 3, modifier = Modifier.fillMaxWidth())

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Green1)
                .padding(16.dp),
        contentAlignment = Alignment.Center
        ){
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp),
            ) {

                item {
                    Spacer(modifier = Modifier.height(20.dp))            }
                item {
                    Text(
                        text = "Registration Successful",
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        color = Color.White,
                        fontSize = if (screenSize == ScreenSize.SMALL) 20.sp else 25.sp
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
                        fontSize = if (screenSize == ScreenSize.SMALL) 15.sp else 18.sp
                    )
                }
                8
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
                            viewModel.setBackPressed2(true)
                            navController.navigate("signUp")

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