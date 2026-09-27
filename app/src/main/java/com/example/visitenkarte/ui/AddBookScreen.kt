package com.example.visitenkarte.ui

import android.widget.Toast
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun AddBookScreen(modifier: Modifier = Modifier) {
    val inputValue = remember { mutableStateOf("") }
    val toast = Toast.makeText(LocalContext.current.applicationContext,"Added to Library",Toast.LENGTH_LONG)

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
        .fillMaxSize()
        .padding(8.dp)
    ){
        OutlinedTextField(
            value = inputValue.value,
            onValueChange = {inputValue.value = it},
            label = {Text("ISBN: ")},
            placeholder = {Text("ISBN Eintragen")},

        )
        Button(
            onClick = { toast.show()},
            content = {Text("Add Book to Digital Library")}
        )
    }


}