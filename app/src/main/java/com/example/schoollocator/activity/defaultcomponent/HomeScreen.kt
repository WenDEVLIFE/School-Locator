package com.example.schoollocator.activity.defaultcomponent

import androidx.annotation.DrawableRes
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.Typography
import com.example.schoollocator.ui.theme.lightgreen
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.ui.theme.materialLightGreen

@Composable
fun Profile(modifier: Modifier = Modifier) {
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
        Column {
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "Furina", style = Typography.bodySmall, fontSize = 20.sp, color = materialGreen)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = "furina45@gmail.com", style = Typography.bodySmall, fontSize = 20.sp, color = materialGreen)
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

@Composable
fun Menu(modifier: Modifier = Modifier) {
    val menuItems = listOf(
        MenuItem(R.drawable.eye, "Map",Icons.Default.KeyboardArrowRight) { /* Handle Home click */ },
        MenuItem(R.drawable.eye, "User",Icons.Default.KeyboardArrowRight) { /* Handle Settings click */ },
        MenuItem(R.drawable.eye, "School",Icons.Default.KeyboardArrowRight) { /* Handle Settings click */ },
        MenuItem(R.drawable.eye, "Add Schools",Icons.Default.KeyboardArrowRight) { /* Handle Logout click */ },
        MenuItem(R.drawable.eye, "Created School",Icons.Default.KeyboardArrowRight) { /* Handle Settings click */ },
        MenuItem(R.drawable.eye, "Messages",Icons.Default.KeyboardArrowRight) { /* Handle Profile click */ },
        MenuItem(R.drawable.eye, "Favorites",Icons.Default.KeyboardArrowRight) { /* Handle Profile click */ },
        MenuItem(R.drawable.eye, "Change Password",Icons.Default.KeyboardArrowRight) { /* Handle Settings click */ },
        MenuItem(R.drawable.eye, "Change Email",Icons.Default.KeyboardArrowRight) { /* Handle Settings click */ },
        MenuItem(R.drawable.eye, "Change Profile Picture",Icons.Default.KeyboardArrowRight) { /* Handle Settings click */ },
        MenuItem(R.drawable.eye, "Logout",Icons.Default.KeyboardArrowRight) { /* Handle Settings click */ },
    )
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ensure LazyColumn fills the remaining space
            .padding(11.dp)
            .background(lightgreen),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
    ) {
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
                    modifier = Modifier.size(32.dp),
                    tint = materialGreen
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.label,
                    style = Typography.bodyLarge,
                    color = materialGreen
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = item.trailingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = materialGreen
                )
            }
        }
    }
}

// This is our data class
data class MenuItem(
    @DrawableRes val icon: Int,
    val label: String,
    val trailingIcon: ImageVector,
    val onClick: () -> Unit
)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(lightgreen)
    ) {
        // call the profile
        Profile()

        // call the menu
        Menu(modifier = Modifier.weight(1f)) // Ensure Menu takes up remaining space
    }
}


// This is for the preview only
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview
@Composable
fun ProfilePreview() {
    Profile()
}

@Preview
@Composable
fun MenuPreview(){
    Menu()
}