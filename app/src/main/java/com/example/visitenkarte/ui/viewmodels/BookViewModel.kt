package com.example.visitenkarte.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.visitenkarte.data.local.entity.BookEntity

class BookViewModel(
//    private val bookDao: BookDao
) : ViewModel() {
    var book by mutableStateOf<BookEntity?>(null)

    fun loadBook(){
//        viewModelScope.launch {
//            bookDao.getAllBooks()
//        }
    }
}