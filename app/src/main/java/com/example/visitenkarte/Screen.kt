package com.example.visitenkarte

sealed class Screen {
    data object Home: Screen()
    data class Details(val id: String):Screen()
}