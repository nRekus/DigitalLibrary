package com.example.visitenkarte

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.visitenkarte.helper.toBitmap
import com.example.visitenkarte.model.Book


@Composable
fun BookCoverCard(currentBook: Book, modifier: Modifier = Modifier,onCloseFunction:()->Unit) {
    val screenHeightDp: Dp = LocalConfiguration.current.screenHeightDp.dp
    val topAndBottomPadding = screenHeightDp * 0.1f
    val (isFront, setIsFront) = remember { mutableStateOf(true) }
    Card(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(0.882f)
            .padding(24.dp, topAndBottomPadding),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),


        ) {
        BookCoverCardHeader(setIsFront, isFront, modifier, onCloseFunction)
        BookCoverCardContent(currentBook,modifier, isFront)
    }

}

@Composable
private fun BookCoverCardHeader(
    setIsFront: (Boolean) -> Unit,
    isFront: Boolean,
    modifier: Modifier,
    onCloseFunction: () -> Unit
) {
    Row(
        Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { setIsFront(!isFront) }
        ) {
            Icon(
                painter = painterResource(R.drawable.cached_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                contentDescription = "Rotate Card"
            )
        }
        Text(
            "Buch Info",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(12.dp)
        )
        IconButton(
            onClick = onCloseFunction,
        ) {
            Icon(
                painter = painterResource(R.drawable.close_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                contentDescription = "Closing Bookcover Card"
            )
        }
    }
}

@Composable
fun BookCoverCardContent(currentBook: Book,modifier: Modifier = Modifier, isFront: Boolean = true) {
    if (isFront) {
        Image(
            bitmap = currentBook.frontCoverByteArray.toBitmap().asImageBitmap(),
            contentDescription = "Book Cover Image",
            contentScale = ContentScale.FillHeight,
            modifier = modifier.fillMaxWidth()
        )
    } else {
        BookInfoCard(currentBook)
    }
}

@Composable
fun BookInfoCard(currentBook:Book) {

    Column {
        Text(
            text = currentBook.originalTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )
        HorizontalDivider(thickness = 3.dp, modifier = Modifier.padding(12.dp))
        Column {
            Row(
                modifier = Modifier
                    .padding(start = 15.dp, top = 8.dp, end = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Publisher:")
                Text(currentBook.publisher.toString())
            }

            Row(
                modifier = Modifier
                    .padding(start = 15.dp, top = 8.dp, end = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Author: ")
                Text("${currentBook.author.firstName} ${currentBook.author.lastName}")
            }

            Row(
                modifier = Modifier
                    .padding(start = 15.dp, top = 8.dp, end = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Number of Pages: ")
                Text("${currentBook.numPages}")
            }
            HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(6.dp))
            Column(
                modifier = Modifier
                    .padding(start = 15.dp, top = 8.dp, end = 15.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Backcover Text: ", textAlign = TextAlign.Center)
                Text(currentBook.blurbText)

            }
        }
    }

}
