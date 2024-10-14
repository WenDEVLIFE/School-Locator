package com.example.schoollocator.activity.maincomponent.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.schoollocator.activity.Screens.LogoutDialog
import com.example.schoollocator.activity.Screens.MainMap
import com.example.schoollocator.activity.Screens.MenuScreen
import com.example.schoollocator.activity.Screens.SchoolScreen
import com.example.schoollocator.activity.Screens.UserScreen

// This are for the navigation graph
@Composable
fun NavigationGraph(navController: NavHostController, contentPadding: PaddingValues, dialogState: MutableState<Boolean>, logoutState: MutableState<Boolean>) {

    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "Map") {


        // Map Composable
        composable("Map") {
            MainMap(modifier = Modifier.padding(contentPadding))

        }

        // Home Composable
        composable("Home") {
            MenuScreen(modifier = Modifier.padding(contentPadding), navController = navController)

        }

        // Favorite Composable
        composable("Favorite") {
            Toast.makeText(context, "Favorites", Toast.LENGTH_SHORT).show()
        }

        // User screen composable
        composable("User"){

            UserScreen(modifier = Modifier.padding(contentPadding),navController = navController)

        }

        // Add User Composable
        composable("AddUser") {
            AddUser(modifier = Modifier.padding(contentPadding), navController = navController)

        }

        // School Composable
        composable("School") {
            SchoolScreen(modifier = Modifier.padding(contentPadding), navController = navController)
        }

        // Add School
        composable("AddSchool") {
            AddSchoolScreen(modifier = Modifier.padding(contentPadding), navController = navController)
        }

        // Created School Screen
        composable("CreatedSchool") {
            CreatedSchoolScreen(modifier = Modifier.padding(contentPadding), navController = navController)
        }

        // Favorites Screen
        composable("Favorites") {
            FavoritesScreen(modifier = Modifier.padding(contentPadding), navController = navController)
        }

        // Change password screen
        composable("ChangePassword") {
            ChangePasswordScreen(modifier = Modifier.padding(contentPadding), navController = navController)
        }

        // Change Profile Screen
        composable("ChangeProfile") {
            ChangeProfileScreen(modifier = Modifier.padding(contentPadding), navController = navController)
        }

        // Change Email Screen
        composable("ChangeEmail") {
            ChangeEmailScreen(modifier = Modifier.padding(contentPadding), navController = navController)
        }

        // Logout Composable
        composable("Logout") {
            LogoutDialog(
                dialogState = dialogState,
                logoutState = logoutState,
                navController = navController
            )
        }
    }
}

