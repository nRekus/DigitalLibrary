package com.example.visitenkarte.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.visitenkarte.helper.toBitmap
import com.example.visitenkarte.model.Book
import com.example.visitenkarte.viewmodels.BookViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.visitenkarte.viewmodels.BookUiState

@Composable
fun ListScreen(
    paddingValues: PaddingValues,
    viewModel: BookViewModel = viewModel()
) {
    val selectedBook = remember { mutableStateOf<Book?>(null) }
    val uiState by viewModel.uiState.collectAsState()
    when(uiState){
        is BookUiState.Loading -> {
            Box(){
                CircularProgressIndicator()
            }
        }
        is BookUiState.Error -> {
            ErrorScreen()
        }
        is BookUiState.Success -> {
            BookGrid(paddingValues, uiState, selectedBook)
            selectedBook.value?.let { book ->
                BookCoverCard(book, Modifier) {
                    selectedBook.value = null
                }
            }
        }
    }
}

@Composable
private fun BookGrid(
    paddingValues: PaddingValues,
    uiState: BookUiState,
    selectedBook: MutableState<Book?>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    )
    {
        items((uiState as BookUiState.Success).book) { book ->
            ListScreenItem(book, onClick = { selectedBook.value = book })
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
fun ListScreenItem(book: Book?, onClick: (Book?) -> Unit) {
    val cardHeight = LocalConfiguration.current.screenHeightDp * 0.276
    val cardWidth = LocalConfiguration.current.screenWidthDp * 0.402
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(cardWidth.dp, cardHeight.dp)
            .then(
                if (book != null) {
                    Modifier.clickable { onClick(book) }
                } else {
                    Modifier
                }
            ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
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
        Icon(imageVector = Icons.Outlined.Warning,
            contentDescription = "No Image Provided",
            )
    }
}