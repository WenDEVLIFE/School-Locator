package com.example.schoollocator.activity.maincomponent.ui

import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.schoollocator.R
import com.example.schoollocator.activity.defaultcomponent.AppNavigation1
import com.example.schoollocator.activity.defaultcomponent.Login
import com.example.schoollocator.data.MenuItem
import com.example.schoollocator.ui.theme.Typography
import com.example.schoollocator.ui.theme.lightgreen
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.ui.theme.materialLightGreen
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize

@Composable
fun Profile(modifier: Modifier = Modifier) {

    // for screen size
    val screenSize = getScreenSize()

    Row(
        modifier = modifier
            .fillMaxWidth() // Ensure Profile fills the width
            .background(materialLightGreen)
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
    ) {
        Box(
            modifier = Modifier
                .size(80.dp) // Adjust size as needed
                .clip(CircleShape)
        ) {
            // This is for the profile logo
            Image(
                painter = painterResource(id = R.drawable.furina),
                contentDescription = "Logo",
                modifier = Modifier.fillMaxSize()
            )
        }

        //This is where the user info will display
        Column {
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "Furina", style = Typography.bodySmall, fontSize = if (screenSize == ScreenSize.SMALL) 16.sp else 20.sp, color = materialGreen)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = "furina45@gmail.com", style = Typography.bodySmall, fontSize = if (screenSize == ScreenSize.SMALL) 16.sp else 20.sp, color = materialGreen)
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

@Composable
fun Menu(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    dialogState: MutableState<Boolean>,
    logoutState: MutableState<Boolean>
) {

    // for screen size
    val screenSize = getScreenSize()

    // This is for local context
    val context = LocalContext.current

    // This is our list of menu 
    val menuItems = listOf(

        MenuItem(R.drawable.map, "Map",Icons.Default.KeyboardArrowRight) {
        /* Handle Home click */
            navController.navigate("Map") {
                launchSingleTop = true
                restoreState = true
            }

            Toast.makeText(context, "Map", Toast.LENGTH_SHORT).show()
        },

        MenuItem(R.drawable.user, "User",Icons.Default.KeyboardArrowRight) {
        /* Handle Settings click */
         Toast.makeText(context, "User", Toast.LENGTH_SHORT).show()
            navController.navigate("User") {
                launchSingleTop = true
                restoreState = true
            }
        },

        MenuItem(R.drawable.schoo, "School",Icons.Default.KeyboardArrowRight) {
        /* Handle Settings click */
            Toast.makeText(context, "School", Toast.LENGTH_SHORT).show()
        },

        MenuItem(R.drawable.pencil, "Add Schools",Icons.Default.KeyboardArrowRight) {
        /* Handle Logout click */
            Toast.makeText(context, "Add Schools", Toast.LENGTH_SHORT).show()
            },

        MenuItem(R.drawable.schoo, "Created School",Icons.Default.KeyboardArrowRight) {
        /* Handle Settings click */
            Toast.makeText(context, "Created School", Toast.LENGTH_SHORT).show()
        },

        MenuItem(R.drawable.chats, "Messages",Icons.Default.KeyboardArrowRight) {
        /* Handle Profile click */
            Toast.makeText(context, "Messages", Toast.LENGTH_SHORT).show()
        },

        MenuItem(R.drawable.love, "Favorites",Icons.Default.KeyboardArrowRight) {
        /* Handle Profile click */
         Toast.makeText(context, "Favorites", Toast.LENGTH_SHORT).show()
        },

        MenuItem(R.drawable.key, "Change Password",Icons.Default.KeyboardArrowRight) {
        /* Handle Settings click */
        Toast.makeText(context, "Change Password", Toast.LENGTH_SHORT).show()
        },

        MenuItem(R.drawable.mail, "Change Email",Icons.Default.KeyboardArrowRight) {
        /* Handle Settings click */
            Toast.makeText(context, "Change Email", Toast.LENGTH_SHORT).show()
        },

        MenuItem(R.drawable.images, "Change Profile Picture",Icons.Default.KeyboardArrowRight) { /* Handle Settings click */
            Toast.makeText(context, "Change Profile Picture", Toast.LENGTH_SHORT).show()
        },

        MenuItem(R.drawable.baseline_power_settings_new_24, "Logout",Icons.Default.KeyboardArrowRight) {

        /* Handle Settings click */
            dialogState.value = true
        },
    )
    
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ensure LazyColumn fills the remaining space
            .padding(11.dp)
            .background(lightgreen),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
    ) {
        
        // call the menu items
        items(menuItems) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(lightgreen)
                    .padding(16.dp)
                    .clickable { item.onClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.label,
                    modifier = Modifier.size(if (screenSize == ScreenSize.SMALL) 32.dp else 48.dp),
                    tint = materialGreen
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.label,
                    style = Typography.bodyLarge,
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = if (screenSize == ScreenSize.SMALL) 16.sp else 20.sp,
                    color = materialGreen
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = item.trailingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(if (screenSize == ScreenSize.SMALL) 32.dp else 48.dp),
                    tint = materialGreen
                )
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    val dialogState = remember { mutableStateOf(false) } // Initialize dialog state
    val logoutState = remember { mutableStateOf(false) } // Initialize logout state

    // Go back to map screen
    BackHandler {
       navController.navigate("Map") {
            launchSingleTop = true
            restoreState = true
        }
    }

    if (dialogState.value) {
        LogoutDialog(navController = navController ,dialogState = dialogState, logoutState = logoutState)
    }

    // This is for the logout state
    if (logoutState.value) {
        AppNavigation1()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(lightgreen)
    ) {
        // call the profile
        Profile()

        // call the menu
        Menu(modifier = Modifier.weight(1f),navController,  dialogState = dialogState, logoutState = logoutState) // Ensure Menu takes up remaining space
    }
}


// This is for the preview only
@Preview
@Composable
fun HomeScreenPreview() {

    HomeScreen(modifier = Modifier ,navController = rememberNavController())
}


@Preview
@Composable
fun ProfilePreview() {
    Profile()
}


@Preview
@Composable
fun MenuPreview(){
    Menu(
        modifier = Modifier,
        navController = rememberNavController(),
        dialogState = remember { mutableStateOf(false) },
        logoutState = remember { mutableStateOf(false) }
    )
}