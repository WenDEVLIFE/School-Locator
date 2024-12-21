package com.example.schoollocator.activity.screens

import android.widget.Toast
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.schoollocator.R
import com.example.schoollocator.components.BottomNavigationBar
import com.example.schoollocator.components.LogoutDialog
import com.example.schoollocator.components.TopAppBarScreen
import com.example.schoollocator.data.MenuItem
import com.example.schoollocator.ui.theme.Typography
import com.example.schoollocator.ui.theme.lightgreen
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.ui.theme.materialLightGreen
import com.example.schoollocator.viewmodel.MenuViewModel
import com.example.schoollocator.viewmodel.SessionViewModel
import com.example.schoollocator.windowEnum.ScreenSize
import com.example.schoollocator.windowEnum.getScreenSize


@Composable
fun Profile(modifier: Modifier = Modifier) {
    val sessionViewModel: SessionViewModel = viewModel()
    val menuViewModel: MenuViewModel = viewModel()
    val screenSize = getScreenSize()
    val username = sessionViewModel.username.value
    val email = sessionViewModel.email.value
    val imageUrl by menuViewModel.imageUrl.collectAsState()

    LaunchedEffect(Unit) {
        menuViewModel.fetchImageUrl(username)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(materialLightGreen)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        ) {
            if (imageUrl != null) {
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = "Profile Image",
                    modifier = Modifier.fillMaxSize()
                   // size(50.dp)
                )
            } else {
                // Placeholder or default image
                Image(
                    painter = painterResource(id = R.drawable.furina),
                    contentDescription = "Default Logo",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            if (username != null) {
                Text(text = username, style = Typography.bodySmall, fontSize = if (screenSize == ScreenSize.SMALL) 16.sp else 20.sp, color = materialGreen)
            }
            Spacer(modifier = Modifier.size(4.dp))
            if (email != null) {
                Text(text = email, style = Typography.bodySmall, fontSize = if (screenSize == ScreenSize.SMALL) 16.sp else 20.sp, color = materialGreen)
            }
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
    val sessionViewModel: SessionViewModel = viewModel()
    val role = sessionViewModel.role.value
    val screenSize = getScreenSize()
    val context = LocalContext.current

    // Define the menu items with visibility conditions
    val menuItems = listOf(
        MenuItem(R.drawable.user, "User", Icons.Default.KeyboardArrowRight, {
            navController.navigate("User") {
                launchSingleTop = true
                restoreState = true
            }
            Toast.makeText(context, "User", Toast.LENGTH_SHORT).show()
        }, visible = role != "User"), // Hide for admin
        MenuItem(R.drawable.schoo, "School", Icons.Default.KeyboardArrowRight, {
            navController.navigate("School") {
                launchSingleTop = true
                restoreState = true
            }
        }, visible = role != "User"), //
        MenuItem(R.drawable.schoo, "Created School", Icons.Default.KeyboardArrowRight, {
            navController.navigate("CreatedSchool") {
                launchSingleTop = true
                restoreState = true
            }
        }, visible = role != "User"),
        MenuItem(R.drawable.chats, "Messages", Icons.Default.KeyboardArrowRight, {
            Toast.makeText(context, "Messages", Toast.LENGTH_SHORT).show()
        }),
        MenuItem(R.drawable.love, "Favorites", Icons.Default.KeyboardArrowRight, {
            navController.navigate("Favorites") {
                launchSingleTop = true
                restoreState = true
            }
            Toast.makeText(context, "Favorites", Toast.LENGTH_SHORT).show()
        }),
        MenuItem(R.drawable.key, "Change Password", Icons.Default.KeyboardArrowRight, {
            navController.navigate("ChangePassword") {
                launchSingleTop = true
                restoreState = true
            }
        }),
        MenuItem(R.drawable.mail, "Change Email", Icons.Default.KeyboardArrowRight, {
            navController.navigate("ChangeEmail") {
                launchSingleTop = true
                restoreState = true
            }
        }),
        MenuItem(R.drawable.images, "Change Profile Picture", Icons.Default.KeyboardArrowRight, {
            navController.navigate("ChangeProfile") {
                launchSingleTop = true
                restoreState = true
            }
        }),

    )

    // Filter the menu items based on visibility
    val visibleMenuItems = menuItems.filter { it.visible }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(11.dp)
            .background(lightgreen),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(visibleMenuItems) { item ->
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
fun MenuScreen(modifier: Modifier = Modifier, navController: NavHostController,) { // Corrected type annotation
    val dialogState = remember { mutableStateOf(false) } // Initialize dialog state
    val logoutState = remember { mutableStateOf(false) } // Initialize logout state

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                dialogState = dialogState,
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            // Default or initial content
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(lightgreen)
            ) {
                TopAppBarScreen(modifier = Modifier, tittle ="Menu")
                // call the profile
                Profile()

                // call the menu
                Menu(modifier = Modifier.weight(1f),navController,  dialogState = dialogState, logoutState = logoutState) // Ensure Menu takes up remaining space
            }
        }
    }

    // This is for the dialog state to show the dialog
    if (dialogState.value) {
        LogoutDialog(
            navController = navController,
            dialogState = dialogState,
            logoutState = logoutState,
            route = "Home"
        )
    }

    // This is for the logout state
    if (logoutState.value) {

        logoutState.value = false // Reset the logout state
    }
}

fun DisplayImage(){

}

// This is for the preview only
@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    Menu(
        modifier = Modifier,
        navController = navController,
        dialogState = remember { mutableStateOf(false) },
        logoutState = remember { mutableStateOf(false) }
    )
}

@Preview
@Composable
fun ProfilePreview() {
    Profile()
}

@Preview
@Composable
fun MenuPreview() {
    val navController = rememberNavController()
    Menu(
        modifier = Modifier,
        navController = navController,
        dialogState = remember { mutableStateOf(false) },
        logoutState = remember { mutableStateOf(false) }
    )
}