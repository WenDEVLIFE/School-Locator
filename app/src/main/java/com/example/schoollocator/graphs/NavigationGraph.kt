package com.example.schoollocator.graphs

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.schoollocator.activity.Screens.AddSchoolScreen
import com.example.schoollocator.activity.Screens.AddUser
import com.example.schoollocator.activity.Screens.ChangeEmailScreen
import com.example.schoollocator.activity.Screens.ChangePasswordScreen
import com.example.schoollocator.activity.Screens.ChangeProfileScreen
import com.example.schoollocator.activity.Screens.CreatedSchoolScreen
import com.example.schoollocator.activity.Screens.FavoritesScreen
import com.example.schoollocator.activity.Screens.LoadOTP
import com.example.schoollocator.activity.Screens.LoadSuccess
import com.example.schoollocator.activity.Screens.LoginForm1
import com.example.schoollocator.activity.Screens.MapScreen
import com.example.schoollocator.activity.Screens.MenuScreen
import com.example.schoollocator.activity.Screens.SchoolScreen
import com.example.schoollocator.activity.Screens.SignUpForm
import com.example.schoollocator.activity.Screens.UserScreen
import com.example.schoollocator.activity.maincomponent.components.LogoutDialog

// This is for the navigation graph
@Composable
fun AppNavigation(navController: NavHostController) {

    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "Login") {

        composable("Login") {
            LoginForm1(navController = navController, Modifier.fillMaxWidth())
        }
        composable("signUp") {
            SignUpForm(navController = navController, Modifier.fillMaxWidth())
        }
        composable("OTP") {
            LoadOTP(navController = navController, Modifier.fillMaxWidth())
        }

        composable("success") {
            LoadSuccess(navController = navController, Modifier.fillMaxWidth())
        }
        // Map Composable
        composable("Map") {
            MapScreen(navController = navController)
        }
        // Home Composable
        composable("Home") {
            MenuScreen( navController = navController)
        }

        // Favorite Composable
        composable("Favorite") {
            Toast.makeText(context, "Favorites", Toast.LENGTH_SHORT).show()
            FavoritesScreen( navController = navController)
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

        // Logout Composable
        composable("Logout") {
            LogoutDialog(
                navController = navController
            )
        }
    }
}