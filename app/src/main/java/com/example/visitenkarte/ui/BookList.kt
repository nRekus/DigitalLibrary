package com.example.visitenkarte.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.visitenkarte.Screen
import com.example.visitenkarte.helper.toBitmap
import com.example.visitenkarte.model.Book
import com.example.visitenkarte.viewmodels.BookListUiState
import com.example.visitenkarte.viewmodels.BookListViewModel

@Composable
fun ListScreen(
    paddingValues: PaddingValues,
    viewModel: BookListViewModel = viewModel(),
    backStack: NavBackStack<NavKey>
) {
    val uiState by viewModel.uiState.collectAsState()
    when(uiState){
        is BookListUiState.Loading -> {
            Box(){
                CircularProgressIndicator()
            }
        }
        is BookListUiState.Error -> {
            ErrorScreen()
        }
        is BookListUiState.Success -> {
            BookGrid(paddingValues, uiState, backStack)
        }
    }
}

@Composable
private fun BookGrid(
    paddingValues: PaddingValues,
    uiState: BookListUiState,
    backStack: NavBackStack<NavKey>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    )
    {
        items((uiState as BookListUiState.Success).bookList,key={book -> book.isbn13}) {
            book ->
            ListScreenItem(book = book,
                onClick = {
                    isbn -> backStack.add(Screen.Details(
                isbn = isbn
            ))})
        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Dialog(
        onDismissRequest = {}
    ) { Text("Error") }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ListScreenItem(book: Book?, onClick: (String)->Unit) {
    val cardHeight = LocalConfiguration.current.screenHeightDp * 0.276
    val cardWidth = LocalConfiguration.current.screenWidthDp * 0.402
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(cardWidth.dp, cardHeight.dp)
            .then(
                if (book != null) {
                    Modifier.clickable { onClick(book.isbn13) }
                } else {
                    Modifier
                }
            ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        LinearProgressIndicator(progress = {Math.random().toFloat()},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .absoluteOffset(y = 220.dp),
            drawStopIndicator = {},
            )

        if (book != null) {
            if(book.frontCoverByteArray.isNotEmpty()){
                val bitmap = remember { book.frontCoverByteArray.toBitmap().asImageBitmap() }
                Image(
                    bitmap = bitmap,
                    contentDescription = "Book Frontcover Item",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )
            }else{
                PlaceholderCover(Modifier)
            }

        }
    }
}

@Composable
fun PlaceholderCover(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize().padding(4.dp)){
        Icon(imageVector = Icons.Outlined.Home,
            contentDescription = "No Image Provided",
            )
    }
}