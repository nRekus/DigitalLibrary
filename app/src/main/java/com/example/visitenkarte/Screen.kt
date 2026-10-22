package com.example.visitenkarte

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
sealed class Screen: NavKey {
    @Serializable
    data object Home: Screen()
    @Serializable
    data class DetailsFrontCover(val isbn: String):Screen()
    @Serializable
    data class Details(val isbn: String):Screen()
    @Serializable
    data object AddBook: Screen()
    @Serializable
    data object FinishedBooks: Screen()
    @Serializable
    data object Account:Screen()

}