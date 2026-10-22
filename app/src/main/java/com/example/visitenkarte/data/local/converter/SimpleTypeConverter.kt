package com.example.visitenkarte.data.local.converter

import androidx.room3.ColumnTypeConverter
import androidx.room3.ColumnTypeConverters
import com.example.visitenkarte.model.ReadingStatus
import java.util.Date

object SimpleTypeConverter {
    @ColumnTypeConverter
    fun fromTimestamp(value: Long?) : Date? {
        return value?.let { Date(it) }
    }
    @ColumnTypeConverter
    fun toTimestamp(tmstmp: Date?): Long? {
      return tmstmp?.time
    }
    @ColumnTypeConverter
    fun fromReadingStatus(value: ReadingStatus?): Int?{
        return value?.ordinal
    }
    @ColumnTypeConverter
    fun toReadingStatus(value: Int): ReadingStatus {
        return ReadingStatus.entries[value]
    }
}