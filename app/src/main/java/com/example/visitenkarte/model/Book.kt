package com.example.visitenkarte.model


import java.util.Date


data class Book(
    val isbn9: String = "",
    val isbn13: String = "",
    val title: String,
    val publisher: Publisher,
    val author: Author,
    val numPages: Int,
    val originalTitle: String = title,
    val translators: List<String> = emptyList(),
    val originalReleaseDate: Date,
    val translatedReleaseDate: Date = originalReleaseDate,
    val frontCoverByteArray: ByteArray,
    val blurbText:String,
    val readingStatus: ReadingStatus = ReadingStatus.TBR
    ) {
}