package com.example.visitenkarte.data.local.entity

import androidx.room3.Embedded
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.example.visitenkarte.model.Author
import com.example.visitenkarte.model.ReadingStatus
import java.util.Date

@Entity
data class Book
    (
    @PrimaryKey
    val isbn13: String = "",
    val title: String,
    val publisher: String,
    @Embedded val author: Author,
    val numPages: Int,
    val originalTitle: String = title,
//    val translators: List<String> = listOf(""),
    val originalReleaseDate: Date,
    val translatedReleaseDate: Date = originalReleaseDate,
    val frontCoverByteArray: ByteArray,
    val blurbText:String,
    val readingStatus: ReadingStatus = ReadingStatus.TBR
    )
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (numPages != other.numPages) return false
        if (isbn13 != other.isbn13) return false
        if (title != other.title) return false
        if (publisher != other.publisher) return false
        if (author != other.author) return false
        if (originalTitle != other.originalTitle) return false
       // if (translators != other.translators) return false
        if (originalReleaseDate != other.originalReleaseDate) return false
        if (translatedReleaseDate != other.translatedReleaseDate) return false
        if (!frontCoverByteArray.contentEquals(other.frontCoverByteArray)) return false
        if (blurbText != other.blurbText) return false
        if (readingStatus != other.readingStatus) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numPages
        result = 31 * result + isbn13.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + publisher.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + originalTitle.hashCode()
       // result = 31 * result + translators.hashCode()
        result = 31 * result + originalReleaseDate.hashCode()
        result = 31 * result + translatedReleaseDate.hashCode()
        result = 31 * result + frontCoverByteArray.contentHashCode()
        result = 31 * result + blurbText.hashCode()
        result = 31 * result + readingStatus.hashCode()
        return result
    }
}