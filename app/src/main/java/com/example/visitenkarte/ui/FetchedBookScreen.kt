package com.example.visitenkarte.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.visitenkarte.model.ReadingStatus

@Composable
fun FetchedBookScreen(
    modifier: Modifier = Modifier
) {
    val expanded = remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()) {
        Box(modifier
            .padding(2.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .background(Color.Cyan)
            .border(BorderStroke(2.dp, Color.Black)),
            contentAlignment = Alignment.Center){
            Text("Add Book to your Digital Library",color=Color.hsv(30f,1f,1f))
        }
        Column(modifier
            .background(color = Color(0xFFf7bf94))
        ) {
            Box(modifier.align(Alignment.End)){
                IconButton(
                    onClick = { expanded.value = !expanded.value },
                    modifier
                        .padding(2.dp)
                ) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "asd",
                        modifier.padding(2.dp)
                    )
                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = !expanded.value }
                ) {
                    ReadingStatus.entries.toTypedArray().forEach { rs ->
                        DropdownMenuItem(
                            text = { Text(rs.status) },
                            onClick = { expanded.value = false },
                        )
                    }
                }
            }
            Column(modifier.fillMaxSize(),Arrangement.Center,Alignment.CenterHorizontally){
                Row(horizontalArrangement = Arrangement.Center){
                    Column(
                        modifier.padding(10.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text("Titel: ")
                        Text("Veröffentlichungs-\ndatum: ", textAlign = TextAlign.Start)
                        Text("Autor: ")
                        Text("Verlag: ")
                        Text("Anzahl Seiten: ")
                    }
                    Column(
                        modifier.padding(10.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text("Titel: ")
                        Text("Veröffentlichungs-\ndatum: ", textAlign = TextAlign.End)
                        Text("Autor: ")
                        Text("Verlag: ")
                        Text("Anzahl Seiten: ")
                    }
                }
                Button(
                    onClick = {},
                    modifier = modifier.fillMaxWidth().padding(4.dp),
                    colors = ButtonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color(0xFFf7bf94),
                        disabledContainerColor = Color.Blue,
                        disabledContentColor = Color.DarkGray
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) { Text("Add to Library")}
            }

        }
    }
}

@Preview
@Composable
fun FetchedPreview(){
    FetchedBookScreen()
}