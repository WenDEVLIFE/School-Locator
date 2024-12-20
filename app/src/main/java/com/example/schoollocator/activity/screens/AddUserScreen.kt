package com.example.schoollocator.activity.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.Green1
import com.example.schoollocator.ui.theme.lightgreen
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.viewmodel.AddUserViewModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize


// This is the AddUserScreen
@Composable
fun AddUser(modifier: Modifier = Modifier,
            navController: NavHostController) {
    val dialogState = remember { mutableStateOf(false) } // Initialize dialog state
    val logoutState = remember { mutableStateOf(false) } // Initialize logout state

    // Go back to map screen
    BackHandler {
        navController.popBackStack()
    }

    Scaffold() { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            // Default or initial content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(lightgreen)
            ){

                // Top bar state
                TopAppBarState(modifier = Modifier, tittle ="Add User")
                AddUserForm()


            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserForm(modifier: Modifier=Modifier){

    // for screen size
    val screenSize = getScreenSize()

    // This is the view model
    val viewModel: AddUserViewModel = viewModel()
    val items = listOf("Select a status" to R.drawable.baseline_person_24,
        "Admin" to R.drawable.baseline_person_24,
        "User" to R.drawable.baseline_person_24,
        "School Owner" to R.drawable.baseline_person_24)
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
                text = "Username",
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

        item {
            Spacer(modifier = Modifier.height(5.dp))
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
            Spacer(modifier = Modifier.height(5.dp))
        }
        item {
            TextField(
                value = viewModel.password.value,
                onValueChange = { viewModel.password.value = (it) },
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
        }

        item{
            Spacer(modifier = Modifier.height(5.dp))
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Email",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = materialGreen,
                fontSize = if (screenSize == ScreenSize.SMALL) 22.sp else 25.sp
            )
        }


        item {
            TextField(
                value = viewModel.email.value,
                onValueChange = { viewModel.email.value =(it) },
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
                text = "Status",
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

@Composable
fun Spinner(
    items: List<Pair<String, Int>>, // List of pairs with text and icon resource
    selectedItem: Pair<String, Int>,
    onItemSelected: (Pair<String, Int>) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 400.dp, height = 50.dp) // Set the size of the dropdown
                .background(Color.White, shape = RoundedCornerShape(20.dp))
                .clickable { expanded = true }
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart // Align content to start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = selectedItem.second),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = selectedItem.first)
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = item.second),
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = item.first)
                        }
                    },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}


// This is the preview of the AddUserScreen
@Preview
@Composable
fun AddUserPreview(){
    AddUser(modifier = Modifier ,navController = rememberNavController())
}

@Preview
@Composable
fun AddUserFormPreview(){
    AddUserForm()
}

@Preview
@Composable
fun SpinnerPreview(){
    Spinner(
        items = listOf(
            "Select a status" to R.drawable.baseline_person_24,
            "Admin" to R.drawable.baseline_person_24,
            "User" to R.drawable.baseline_person_24,
            "School Owner" to R.drawable.baseline_person_24
        ),
        selectedItem = "Select a status" to R.drawable.baseline_person_24,
        onItemSelected = {}
    )
}