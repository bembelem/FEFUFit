package com.example.fefufit.data.db

import androidx.room.TypeConverter
import com.example.fefufit.data.enums.ActivityType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Converters {
    private val _formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun fromActivityType(activityType: ActivityType): String {
        return activityType.name
    }

    @TypeConverter
    @JvmStatic
    fun toActivityType(typeName: String): ActivityType {
        return ActivityType.valueOf(typeName)
    }

    @TypeConverter
    @JvmStatic
    fun fromLocalDateTime(date: LocalDateTime): String {
        return date.format(_formatter)
    }

    @TypeConverter
    @JvmStatic
    fun toLocalDateTime(date: String): LocalDateTime {
        return date.let {
            LocalDateTime.parse(it, _formatter)
        }
    }
}