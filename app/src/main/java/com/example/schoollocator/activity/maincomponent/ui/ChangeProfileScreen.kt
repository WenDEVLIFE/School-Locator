package com.example.schoollocator.activity.maincomponent.ui

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.example.schoollocator.viewmodel.ChangeProfileViewModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize

@Composable
fun ChangeProfileScreen(modifier: Modifier = Modifier,
                        navController: NavHostController
) {

    // get screen
    val screenSize = getScreenSize()

    // view model
    val viewModel : ChangeProfileViewModel = viewModel()


    // Go back to home screen
    BackHandler {
        navController.navigate("Home") {
            launchSingleTop = true
            restoreState = true
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(lightgreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(lightgreen),

        ) {
            // Top bar
            TopAppBarState(modifier = Modifier, tittle = "Change Profile")
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item{
                    ChangeProfile(modifier = Modifier.align(Alignment.CenterHorizontally))
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
                            text = "Update Profile",
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

@Composable
fun ChangeProfile(modifier: Modifier = Modifier) {

    val context = LocalContext.current
     Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
     ) {
         Box(
             modifier = modifier
                 .size(200.dp) // Adjust size as needed
                 .clip(CircleShape)
                 .border(width = 2.dp, color = Color.Black, shape = CircleShape)
         ) {
             // This is for the profile logo
             Image(
                 painter = painterResource(id = R.drawable.furina),
                 contentDescription = "Logo",
                 modifier = Modifier.fillMaxSize()
             )

             // This is for the overlay icon
             Icon(
                 //imageVector = Icons.Default.Edit, // Use a predefined icon
                 painter = painterResource(id= R.drawable.baseline_photo_camera_24),
                 contentDescription = "Edit Profile",
                 tint = materialGreen,
                 modifier = Modifier
                     .align(Alignment.BottomEnd)
                     .padding(8.dp)
                     .size(84.dp)
                     .clickable {
                         // Handle icon click
                         Toast.makeText(context, "Edit Profile", Toast.LENGTH_SHORT).show()
                     }
             )
         }
     }
}

@Preview
@Composable
fun ChangeProfileScreenPreview() {
    ChangeProfileScreen(modifier =  Modifier.fillMaxWidth(), navController = rememberNavController())
}