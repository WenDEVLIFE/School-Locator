package com.example.schoollocator.graphs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.schoollocator.activity.screens.AddSchoolScreen
import com.example.schoollocator.activity.screens.AddUser
import com.example.schoollocator.activity.screens.ChangeEmailScreen
import com.example.schoollocator.activity.screens.ChangePasswordScreen
import com.example.schoollocator.activity.screens.ChangeProfileScreen
import com.example.schoollocator.activity.screens.CreatedSchoolScreen
import com.example.schoollocator.activity.screens.FavoritesScreen
import com.example.schoollocator.activity.screens.LoadOTP
import com.example.schoollocator.activity.screens.LoadSuccess
import com.example.schoollocator.activity.screens.LoginForm1
import com.example.schoollocator.activity.screens.MapScreen
import com.example.schoollocator.activity.screens.MenuScreen
import com.example.schoollocator.activity.screens.ChatScreen
import com.example.schoollocator.activity.screens.SchoolScreen
import com.example.schoollocator.activity.screens.SignUpForm
import com.example.schoollocator.activity.screens.Splash
import com.example.schoollocator.activity.screens.UserScreen
import com.example.schoollocator.components.LogoutDialog

// This is for the navigation graph
@Composable
fun AppNavigation(navController: NavHostController) {
    val context = LocalContext.current
    val route = "SplashScreen" // Set the initial route
    val dialogState = remember { mutableStateOf(false) } // Initialize dialog state
    val logoutState = remember { mutableStateOf(false) } // Initialize logout state

    NavHost(navController = navController, startDestination = route) {

        composable("SplashScreen") {
            Splash(navController = navController, Modifier.fillMaxWidth())
        }
        composable("Login") {
            LoginForm1(navController = navController)
        }
        composable("signUp") {
            SignUpForm(navController = navController, Modifier.fillMaxWidth())
        }
        composable("otp/{jsonString}") { backStackEntry ->

            // This is how you pass a data using map use jsonString
            val jsonString = backStackEntry.arguments?.getString("jsonString")
            LoadOTP(navController = navController, Modifier.fillMaxWidth(), jsonMapString = jsonString ?: "")
        }

        composable("success") {
            LoadSuccess(navController = navController, Modifier.fillMaxWidth())
        }
        // Map Composable
        composable("Map/{jsonString}") { backStackEntry ->
            val jsonString = backStackEntry.arguments?.getString("jsonString")
            MapScreen(navController = navController, jsonMapString = jsonString ?: "")
        }
        // Home Composable
        composable("Home") {
            MenuScreen( navController = navController )
        }

        // User screen composable
        composable("User") {
            UserScreen( navController = navController)
        }

        // Add User Composable
        composable("AddUser") {
            AddUser(navController = navController)
        }

        // School Composable
        composable("School") {
            SchoolScreen( navController = navController)
        }

        // Add School
        composable("AddSchool") {
            AddSchoolScreen( navController = navController)
        }

        // Created School Screen
        composable("CreatedSchool") {
            CreatedSchoolScreen( navController = navController)
        }

        // Favorites Screen
        composable("Favorites") {
            FavoritesScreen( navController = navController)
        }

        // Change password screen
        composable("ChangePassword") {
            ChangePasswordScreen( navController = navController)
        }

        // Change Profile Screen
        composable("ChangeProfile") {
            ChangeProfileScreen( navController = navController)
        }

        // Change Email Screen
        composable("ChangeEmail") {
            ChangeEmailScreen( navController = navController)
        }

        // Message Screen
        composable("Chats") {
            ChatScreen( navController = navController)
        }

        // Logout Composable
        composable("Logout") {
            LogoutDialog(
                navController = navController,
                dialogState = dialogState,
                logoutState = logoutState,
                route = route,
            )
        }
    }
}