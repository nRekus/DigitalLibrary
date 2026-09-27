package com.example.visitenkarte.data.local.dao

//import androidx.room3.Dao
//import androidx.room3.Delete
//import androidx.room3.Insert
//import androidx.room3.Query
import com.example.visitenkarte.data.local.entity.BookEntity

//@Dao
interface BookDao {

  //  @Query("SELECT * FROM BOOK")
    suspend fun getAllBooks():List<BookEntity>

    //@Query("Select * FROM BOOK WHERE isbn = :isbn")
    suspend fun getBookByISBN(isbn:String):List<BookEntity?>

    //@Insert
    suspend fun addBook(book:BookEntity)

    //@Delete
    suspend fun deleteBook(book:BookEntity)

}