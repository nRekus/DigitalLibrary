package com.example.visitenkarte.model

enum class ReadingStatus(val status: String) {
    TBR("Want to Read"),
    FINISHED("Finished"),
    CURRENT("Currently Reading"),
    ABORTED("Aborted/Canceled"),
    PAUSED("Paused")
}