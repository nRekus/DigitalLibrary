package com.example.visitenkarte.data.local.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.visitenkarte.data.local.entity.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM BOOK")
    suspend fun getAllBooks(): List<Book>

    @Query("Select * FROM BOOK WHERE isbn13 = :isbn")
    suspend fun getBookByISBN(isbn:String):Book?

    @Insert
    suspend fun addBook(book:Book)

    @Delete
    suspend fun deleteBook(book:Book)

    @Update
    suspend fun updateBook(book:Book)

}