package com.example.schoollocator.activity.defaultcomponent

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.lightgreen
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
            Text(text = "Furina")
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = "furina45@gmail.com")
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

@Composable
fun Menu(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ensure LazyColumn fills the remaining space
            .padding(11.dp)
            .background(lightgreen),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
    ) {
        items(10) {
            Text(text = "Item $it")
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Profile()
        Menu(modifier = Modifier.weight(1f)) // Ensure Menu takes up remaining space
    }
}

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