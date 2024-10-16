package com.example.schoollocator.activity.Screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.schoollocator.activity.maincomponent.components.BottomNavigationBar
import com.example.schoollocator.activity.maincomponent.components.LogoutDialog
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.lightgreen
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.viewmodel.AddUserViewModel
import com.example.schoollocator.viewmodel.ChangeEmailViewModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize

@Composable
fun ChangeEmailScreen(modifier: Modifier = Modifier,
                       navController: NavHostController
){

    val viewModel: AddUserViewModel = viewModel()
    val dialogState = remember { mutableStateOf(false) } // Initialize dialog state
    val logoutState = remember { mutableStateOf(false) } // Initialize logout state
    // Go back to map screen
    BackHandler {
        navController.popBackStack()
    }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, dialogState = dialogState)
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            // Default or initial content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(lightgreen)
            ){

                // Top bar state
                TopAppBarState(modifier = Modifier, tittle ="Change Email")

                Spacer(modifier = Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center

                ) {
                    ChangeEmailForm()
                }


            }
        }
    }

    // This is for the dialog state to show the dialog
    if (dialogState.value) {
        LogoutDialog(
            navController = navController,
            dialogState = dialogState,
            logoutState = logoutState,
            route = "ChangeEmail"
        )
    }

    // This is for the logout state
    if (logoutState.value) {
        logoutState.value = false // Reset the logout state
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeEmailForm(modifier: Modifier = Modifier){

    // for screen size
    val screenSize = getScreenSize()

    // This is the view model
    val viewModel: ChangeEmailViewModel = viewModel()

    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ensure LazyColumn fills the remaining space
            .padding(11.dp)
            .background(lightgreen),
        verticalArrangement = if (screenSize == ScreenSize.SMALL) Arrangement.spacedBy(10.dp) else Arrangement.spacedBy(20.dp) // Add spacing between items
    ) {
        item {
            Spacer(modifier = Modifier.height(26.dp))
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Old Email",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = materialGreen,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )
        }


        item {
            TextField(
                value = viewModel.Oldemail.value,
                onValueChange = { viewModel.Oldemail.value =(it) },
                placeholder = { Text(text = "Enter your old email") },

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

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "New Email",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = materialGreen,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )
        }


        item {
            TextField(
                value = viewModel.Newemail.value,
                onValueChange = { viewModel.Newemail.value =(it) },
                placeholder = { Text(text = "Enter your new email") },

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

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Password",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = materialGreen,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )
        }

        item {
            TextField(
                value = viewModel.Password.value,
                onValueChange = { viewModel.Password.value = (it) },
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
                    val image = if (viewModel.Passwordvisibility.value)
                        painterResource(id = R.drawable.see)
                    else
                        painterResource(id = R.drawable.eye)

                    IconButton(onClick = { viewModel.onPasswordVisibilityClick() }) {
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
                visualTransformation = if (viewModel.Passwordvisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        item{
            Spacer(modifier = Modifier.height(5.dp))
        }

        item {
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                Text(
                    text = "Update Email",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    color = Green1,
                    fontSize = if (screenSize == ScreenSize.SMALL) 20.sp else 25.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun ChangeEmailScreenPreview(){
    ChangeEmailScreen(modifier = Modifier, navController = rememberNavController())
}