package com.example.schoollocator.activity.defaultcomponent

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoollocator.R
import com.example.schoollocator.ui.theme.materialGreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Row(modifier = modifier.background(materialGreen)) {
        Box(
            modifier = Modifier
                .size(50.dp) // Adjust size as needed
                .clip(CircleShape)
        ) {

            // This is for the profile logo
            Image(
                painter = painterResource(id = R.drawable.furina),
                contentDescription = "Logo",
                modifier = Modifier.fillMaxSize()
            )

        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp) // Adjust padding as needed
        ) {
            Text(text = "Furina")
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = "furina45@gmail.com")
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
    LazyColumn {
        // Add items here
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}