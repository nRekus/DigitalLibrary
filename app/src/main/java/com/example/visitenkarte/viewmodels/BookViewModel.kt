package com.example.visitenkarte.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.visitenkarte.data.local.DummyRepository
import com.example.visitenkarte.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface BookUiState {
    data object Loading : BookUiState
    data class Success(val book: Book?): BookUiState
    data class Error(val message: String): BookUiState
}

class BookViewModel(): ViewModel(){
    private val _uiState = MutableStateFlow<BookUiState>(BookUiState.Loading)
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()
    private val dummyRepo : DummyRepository = DummyRepository()

    fun loadBookByISBN(isbn:String){
        viewModelScope.launch {
            _uiState.value = BookUiState.Loading
            try {
                val book = dummyRepo.getBookByISBN(isbn)
                _uiState.value = BookUiState.Success(book)
            }catch (e: Exception){
                _uiState.value = BookUiState.Error("Kein Buch mit ISBN: ${isbn} gefunden.")
            }
        }
    }

//    companion object {
//        val Factory = viewModelFactory {
//            initializer {
//                val application = this[APPLICATION_KEY]!!
//                val repository = DummyRepository()//(application.applicationContext)
//                BookViewModel(repository)
//            }
//        }
//    }

}