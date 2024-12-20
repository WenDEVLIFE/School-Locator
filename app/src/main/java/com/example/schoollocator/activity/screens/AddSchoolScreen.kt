package com.example.schoollocator.activity.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.lightgreen
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.viewmodel.AddSchoolViewModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize

@Composable
fun AddSchoolScreen(modifier: Modifier = Modifier , navController: NavHostController){


    val dialogState = remember { mutableStateOf(false) } // Initialize dialog state
    val logoutState = remember { mutableStateOf(false) } // Initialize logout state
    // Go back to map screen
    BackHandler {
        navController.navigate("School") {
            launchSingleTop = true
            restoreState = true
        }
    }

    Scaffold() { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(lightgreen)
            ) {

                // Top bar state
                TopAppBarState(modifier = Modifier, tittle = "Add School")
                AddSchoolForm()


            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSchoolForm(modifier: Modifier=Modifier){

    // for screen size
    val screenSize = getScreenSize()


    // This is the view model
    val viewModel: AddSchoolViewModel = viewModel()
    val   items = listOf(
        "Select a school status" to R.drawable.graduate,
        "Kinder garden" to R.drawable.graduate,
        "Elementary" to R.drawable.graduate,
        "Junior High School" to R.drawable.graduate,
        "Senior High" to R.drawable.graduate,
        "College/University" to R.drawable.graduate
    )
    var selectedItem by remember { mutableStateOf(items[0]) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ensure LazyColumn fills the remaining space
            .padding(11.dp)
            .background(lightgreen),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
    ) {
        item {
            Spacer(modifier = Modifier.height(26.dp))
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "School name",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = materialGreen,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )
        }

        item {
            Spacer(modifier = Modifier.height(5.dp))
        }

        item {
            TextField(
                value = viewModel.username.value,
                onValueChange = {  viewModel.username.value = (it) },
                placeholder = { Text(text = "Enter the school name") },
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
                        painter = painterResource(id = R.drawable.pencil),
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

        item {
            Spacer(modifier = Modifier.height(5.dp))
        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Descrption",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = materialGreen,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )
        }

        item {
            Spacer(modifier = Modifier.height(5.dp))
        }
        item {
            TextField(
                value = viewModel.schoolAbout.value,
                onValueChange = { viewModel.schoolAbout.value =(it) },
                placeholder = { Text(text = "Enter a description about your school") },

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
                text = "School Address",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = materialGreen,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )
        }

        item {
            Spacer(modifier = Modifier.height(5.dp))
        }

        item {
            TextField(
                value = viewModel.schoolAddress.value,
                onValueChange = { viewModel.schoolAddress.value =(it) },
                placeholder = { Text(text = "Enter school address") },

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

        item{
            Spacer(modifier = Modifier.height(5.dp))
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "School Email",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = materialGreen,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )
        }


        item {
            TextField(
                value = viewModel.schoolEmail.value,
                onValueChange = { viewModel.schoolEmail.value =(it) },
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

        item{
            Spacer(modifier = Modifier.height(5.dp))
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "School Status",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = materialGreen,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )
        }

        item{
            Spinner(
                items = items,
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it },
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
                    text = "Add User",
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
fun SpinnerPreview1(){
    Spinner(
        items = listOf(
            "Select a school status" to R.drawable.graduate,
            "Kinder garden" to R.drawable.graduate,
            "Elementary" to R.drawable.graduate,
            "Junior High School" to R.drawable.graduate,
            "Senior High" to R.drawable.graduate,
            "College/University" to R.drawable.graduate
        ),
        selectedItem = "Select a school status" to R.drawable.graduate,
        onItemSelected = {}
    )
}


@Preview
@Composable
fun AddSchoolFormPreview(){
    AddSchoolScreen(modifier = Modifier, navController = rememberNavController())
}
