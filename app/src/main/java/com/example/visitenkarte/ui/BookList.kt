package com.example.visitenkarte.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.visitenkarte.R
import com.example.visitenkarte.helper.drawableResourceToBitmap
import com.example.visitenkarte.helper.toBitmap
import com.example.visitenkarte.helper.toByteArray
import com.example.visitenkarte.model.Author
import com.example.visitenkarte.model.Book
import com.example.visitenkarte.model.Publisher
import com.example.visitenkarte.model.ReadingStatus
import java.util.Date

@Composable
fun ListScreen(paddingValues: PaddingValues) {
    //Dummy
    val ctx = LocalContext.current.resources
    val booksList =  remember {
        mutableListOf(
            Book(
                title = "Star Wars Dark Lord The Rise of Darth Vader",
                publisher = Publisher.BENVELET,
                numPages = 643,
                originalReleaseDate = Date(),
                frontCoverByteArray = drawableResourceToBitmap(
                    ctx,
                    R.drawable.tmp_img
                ).toByteArray(),
                blurbText = "The Text from the Back Cover of The Book",
                author = Author("James", "Luceno", "01-04-1947")
            ),
            Book(
                title = "Star Wars Tarkin",
                publisher = Publisher.BENVELET,
                numPages = 643,
                originalReleaseDate = Date(),
                frontCoverByteArray = drawableResourceToBitmap(
                    ctx,
                    R.drawable.tarkin
                ).toByteArray(),
                blurbText = "The Text from the Back Cover of The Book",
                author = Author("James", "Luceno", "01-04-1947")
            ),
            Book(
                title = "Star Wars Darth Plagueis",
                publisher = Publisher.BENVELET,
                numPages = 756,
                originalReleaseDate = Date(),
                frontCoverByteArray = drawableResourceToBitmap(
                    ctx,
                    R.drawable.star_wars_darth_plagueis_taschenbuch_james_luceno
                ).toByteArray(),
                blurbText = "asdf",
                author = Author("James", "Luceno", "01-04-1947"),
                readingStatus = ReadingStatus.FINISHED
            )
        )
    }

    val selectedBook = remember { mutableStateOf<Book?>(null) }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    )
    {
            items(booksList) { book1 ->
                ListScreenItem(book1, onClick = { selectedBook.value = book1 })
            }
        }
    selectedBook.value?.let { book ->
        BookCoverCard(book, Modifier) {
            selectedBook.value = null
        }
    }
}

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
            Image(
                bitmap = book.frontCoverByteArray.toBitmap().asImageBitmap(),
                contentDescription = "Book Frontcover Item",
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
        }
    }


}