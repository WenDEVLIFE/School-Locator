package com.example.schoollocator.activity.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.schoollocator.R
import com.example.schoollocator.components.BottomNavigationBar
import com.example.schoollocator.components.LogoutDialog
import com.example.schoollocator.components.TopAppBarScreen
import com.example.schoollocator.data.MenuItem
import com.example.schoollocator.ui.theme.Typography
import com.example.schoollocator.ui.theme.darkblue
import com.example.schoollocator.ui.theme.lightgreen
import com.example.schoollocator.ui.theme.materialGreen
import com.example.schoollocator.ui.theme.materialLightGreen
import com.example.schoollocator.ui.theme.white900
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
            .background(white900)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageUrl ?: R.drawable.furina, // Default image if `imageUrl` is null
                contentDescription = "Profile Image",
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.baseline_cloud_sync_24), // Loading placeholder
                error = painterResource(id = R.drawable.baseline_sync_problem_24) // Error placeholder
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            if (username != null) {
                Text(text = username, style = Typography.bodySmall, fontSize = if (screenSize == ScreenSize.SMALL) 16.sp else 20.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.size(4.dp))
            if (email != null) {
                Text(text = email, style = Typography.bodySmall, fontSize = if (screenSize == ScreenSize.SMALL) 16.sp else 20.sp, color = Color.Black)
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
        MenuItem(R.drawable.chats, "Chats", Icons.Default.KeyboardArrowRight, {

            navController.navigate("Chats") {
                launchSingleTop = true
                restoreState = true
            }
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
            .background(white900),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(visibleMenuItems) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(white900)
                    .padding(16.dp)
                    .clickable { item.onClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.label,
                    modifier = Modifier.size(if (screenSize == ScreenSize.SMALL) 32.dp else 48.dp),
                    tint = darkblue
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.label,
                    style = Typography.bodyLarge,
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = if (screenSize == ScreenSize.SMALL) 16.sp else 20.sp,
                    color = darkblue
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = item.trailingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(if (screenSize == ScreenSize.SMALL) 32.dp else 48.dp),
                    tint = darkblue
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
                    .background(white900)
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