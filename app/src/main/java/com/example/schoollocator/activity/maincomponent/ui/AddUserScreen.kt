package com.example.schoollocator.activity.maincomponent.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.schoollocator.ui.theme.lightgreen


// This is the AddUserScreen
@Composable
fun AddUser(modifier: Modifier=Modifier){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightgreen)
    ){

        // Top bar state
        TopAppBarState(modifier =Modifier, tittle ="Add User")

    }

}


// This is the preview of the AddUserScreen
@Preview
@Composable
fun AddUserPreview(){
    AddUser()
}