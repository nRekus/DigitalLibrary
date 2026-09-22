package com.example.visitenkarte

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.visitenkarte.helper.toBitmap
import com.example.visitenkarte.model.Book

@Composable
fun ListScreen(paddingValues: PaddingValues, booksList: List<Book>) {
        val selectedBook = remember { mutableStateOf<Book?>(null) }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
        {
            items(booksList.chunked(2)) { pair ->
                val book1: Book? = pair.getOrNull(0)
                val book2: Book? = pair.getOrNull(1)
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    ListScreenItem(book1,onClick = {book1:Book? -> selectedBook.value = book1})
                    ListScreenItem(book2, onClick = { book2:Book? -> selectedBook.value = book2 })
                }
            }
        }
        selectedBook.value?.let { book -> BookCoverCard(book,Modifier,{selectedBook.value = null}) }
    }




@Composable
fun ListScreenItem(book: Book?, onClick: (Book?)->Unit) {
    val cardHeight = LocalConfiguration.current.screenHeightDp * 0.276
    val cardWidth = LocalConfiguration.current.screenWidthDp * 0.402
    Card(
            modifier = Modifier
                .padding(8.dp)
                .size(cardWidth.dp, cardHeight.dp)
                .then(
                    if (book!=null){
                        Modifier.clickable { onClick(book) }
                    }else{
                        Modifier
                    }
                )
                ,
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            if (book != null) {
                Image(
                    bitmap = book.frontCoverByteArray.toBitmap().asImageBitmap(),
                    contentDescription = "Tester",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )
            }
        }


}