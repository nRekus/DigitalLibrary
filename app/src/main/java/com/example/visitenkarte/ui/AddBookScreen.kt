package com.example.visitenkarte.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.visitenkarte.R

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
            placeholder = {Text("ISBN Eintragen", color = Color.Black)},
            textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = TextUnit.Unspecified
                ),
            //modifier = modifier.background(Color.Black),
            trailingIcon = {
                IconButton(onClick = {toast.show()},
                    modifier.background(Color.Black).padding(4.dp)
                    ,content = {
                Icon(painterResource( R.drawable.photo_camera_32dp_e3e3e3_fill0_wght400_grad0_opsz40),"Camera")
                    })}

        )
        Button(
            onClick = { toast.show()},
            content = {Text("Add Book to Digital Library")}
        )
    }


}