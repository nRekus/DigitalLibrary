package com.example.visitenkarte.data.local.mapper

import com.example.visitenkarte.data.local.entity.Book
import com.example.visitenkarte.model.Book as BookModel

object BookMapper {
    fun EntityToModel(book: Book): BookModel {
        return BookModel(
            isbn13 = book.isbn13,
            title = book.title,
            publisher = book.publisher,
            author = book.author,
            numPages = book.numPages,
            originalReleaseDate = book.originalReleaseDate,
            frontCoverByteArray = book.frontCoverByteArray,
            blurbText = book.blurbText
        )
    }
}