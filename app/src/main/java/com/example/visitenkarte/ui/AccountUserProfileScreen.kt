package com.example.visitenkarte.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

@Composable
fun UserScreen(modifier: Modifier = Modifier,
               backStack: NavBackStack<NavKey>)
{
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center

        ) {
            IconButton(
                onClick = {backStack.removeLastOrNull()},
            ) { Icon(Icons.AutoMirrored.Filled.ArrowBack,"")}
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account Circle Icon",
                modifier = modifier.size(80.dp)
            )
        }
        Box(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(25.dp))
                .background(Color.Blue)
        ){
            Column(
                modifier = modifier.fillMaxSize()
            ) { }
        }

    }
}