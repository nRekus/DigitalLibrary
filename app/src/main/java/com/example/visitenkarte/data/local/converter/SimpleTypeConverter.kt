package com.example.visitenkarte.data.local.converter

import com.example.visitenkarte.model.ReadingStatus
import java.sql.Date

object SimpleTypeConverter {

    fun fromTimestamp(value: Long?) : Date? {
        return value?.let { Date(it) }
    }

    fun toTimestamp(tmstmp: Date?): Long? {
      return tmstmp?.time
    }

    fun fromReadingStatus(value: ReadingStatus?): Long?{
        return value?.ordinal?.toLong()
    }

    fun toReadingStatus(value: Long?): ReadingStatus {
        return ReadingStatus.entries[value?.toInt()!!]
    }
}