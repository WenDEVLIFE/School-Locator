package com.example.schoollocator.activity.maincomponent.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoollocator.ui.theme.Green1
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
        AddUserForm()


    }

}

@Composable
fun AddUserForm(modifier: Modifier=Modifier){
    Box(   modifier = modifier
        .fillMaxSize()
        .background(lightgreen)
        .padding(16.dp)){
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)

        ) {
            items(10){
                Text(text = "Hello")
            }

        }
    }

}


// This is the preview of the AddUserScreen
@Preview
@Composable
fun AddUserPreview(){
    AddUser()
}