package com.example.visitenkarte.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
        Column(modifier.fillMaxWidth().background(Color.Red)) {
            IconButton(
                onClick = { expanded.value = !expanded.value },
                modifier.padding(2.dp).align(alignment = Alignment.End)
            ) { Icon(Icons.Default.MoreVert,
                contentDescription="asd",
                modifier.padding(2.dp))
                }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = {expanded.value = !expanded.value},
                modifier.padding(2.dp).align(Alignment.End)
            ) {
                    for (rs in ReadingStatus.entries.toTypedArray()) {
                        DropdownMenuItem(
                            text = { Text(rs.status) },
                            onClick = { expanded.value = false },
                        )
                    }
            }
        }
    }
}

@Preview
@Composable
fun FetchedPreview(){
    FetchedBookScreen()
}