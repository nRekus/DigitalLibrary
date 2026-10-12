package com.example.visitenkarte.viewmodels

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.visitenkarte.R
import com.example.visitenkarte.helper.drawableResourceToBitmap
import com.example.visitenkarte.helper.toByteArray
import com.example.visitenkarte.model.Author
import com.example.visitenkarte.model.Book
import com.example.visitenkarte.model.Publisher
import com.example.visitenkarte.model.ReadingStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

sealed interface BookUiState {
    data object Loading : BookUiState
    data class Success(val book: List<Book>): BookUiState
    data class Error(val message: String): BookUiState
}


class BookViewModel(
//    private val bookDao: BookDao
  // private val application: Application
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookUiState>(BookUiState.Loading)
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()


    init{
        loadAllBooks()
    }

    private fun loadAllBooks() {
        viewModelScope.launch {
            _uiState.value = BookUiState.Loading
            try {
                val bookList = mutableListOf(
                    Book(
                        title = "Star Wars Dark Lord The Rise of Darth Vader",
                        publisher = Publisher.BENVELET,
                        numPages = 643,
                        originalReleaseDate = Date(),
//                        frontCoverByteArray = drawableResourceToBitmap(
//                            resources,
//                            R.drawable.tmp_img
//                        ).toByteArray(),
                        frontCoverByteArray = byteArrayOf(),
                        blurbText = "The Text from the Back Cover of The Book",
                        author = Author("James", "Luceno", "01-04-1947")
                    ),
                    Book(
                        title = "Star Wars Tarkin",
                        publisher = Publisher.BENVELET,
                        numPages = 643,
                        originalReleaseDate = Date(),
//                        frontCoverByteArray = drawableResourceToBitmap(
//                            resources,
//                            R.drawable.tarkin
//                        ).toByteArray(),
                        frontCoverByteArray = byteArrayOf(),
                        blurbText = "The Text from the Back Cover of The Book",
                        author = Author("James", "Luceno", "01-04-1947")
                    ),
                    Book(
                        title = "Star Wars Darth Plagueis",
                        publisher = Publisher.BENVELET,
                        numPages = 756,
                        originalReleaseDate = Date(),
//                        frontCoverByteArray = drawableResourceToBitmap(
//                            resources,
//                            R.drawable.star_wars_darth_plagueis_taschenbuch_james_luceno
//                        ).toByteArray(),
                        frontCoverByteArray = byteArrayOf(),
                        blurbText = "asdf",
                        author = Author("James", "Luceno", "01-04-1947"),
                        readingStatus = ReadingStatus.FINISHED
                    )
                )
                _uiState.value = BookUiState.Success(bookList)
            } catch (e: Exception){
                _uiState.value = BookUiState.Error("Fehler beim laden der Liste")
            }
        }

    }

    fun refresh(){
        loadAllBooks()
    }

    fun loadBook(isbn:String){
//        viewModelScope.launch {
//            bookDao.getAllBooks()
//        }
    }
}