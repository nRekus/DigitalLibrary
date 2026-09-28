package com.example.visitenkarte.data.local.mapper

import com.example.visitenkarte.data.local.entity.BookEntity
import com.example.visitenkarte.model.Book

object BookMapper {
    fun EntityToModel(bookEntity: BookEntity):Book{
        return Book(
            isbn13 = bookEntity.isbn13,
            title = bookEntity.title,
            publisher = bookEntity.publisher,
            author = bookEntity.author,
            numPages = bookEntity.numPages,
            originalReleaseDate = bookEntity.originalReleaseDate,
            frontCoverByteArray = bookEntity.frontCoverByteArray,
            blurbText = bookEntity.blurbText
        )
    }
}