package com.example.visitenkarte.data.local.entity

//import androidx.room3.Entity
//import androidx.room3.PrimaryKey
import com.example.visitenkarte.model.Author
import com.example.visitenkarte.model.Publisher
import com.example.visitenkarte.model.ReadingStatus
import java.util.Date

//@Entity
data class BookEntity
    (
    //@PrimaryKey
    val isbn13: String = "",
    val isbn9: String = "",
    val title: String,
    val publisher: Publisher,
    val author: Author,
    val numPages: Int,
    val originalTitle: String = title,
    val translators: List<String> = listOf(""),
    val originalReleaseDate: Date,
    val translatedReleaseDate: Date = originalReleaseDate,
    val frontCoverByteArray: ByteArray,
    val blurbText:String,
    val readingStatus: ReadingStatus = ReadingStatus.TBR
    )
{
}