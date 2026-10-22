package com.example.visitenkarte.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.visitenkarte.data.local.DummyRepository
import com.example.visitenkarte.model.Author
import com.example.visitenkarte.model.Book
import com.example.visitenkarte.model.Publisher
import com.example.visitenkarte.model.ReadingStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

sealed interface BookListUiState {
    data object Loading : BookListUiState
    data class Success(val bookList: List<Book>): BookListUiState
    data class Error(val message: String): BookListUiState
}


class BookListViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow<BookListUiState>(BookListUiState.Loading)
    val uiState: StateFlow<BookListUiState> = _uiState.asStateFlow()
    val dummyRepo: DummyRepository = DummyRepository()
    init{
        loadAllBooks()
    }

    private fun loadAllBooks() {
        viewModelScope.launch {
            _uiState.value = BookListUiState.Loading
            try {
                val bookList = dummyRepo.getAllBooks()
                _uiState.value = BookListUiState.Success(bookList)
            } catch (e: Exception){
                _uiState.value = BookListUiState.Error("Fehler beim laden der Liste")
            }
        }

    }
    fun refresh(){
        loadAllBooks()
    }
}