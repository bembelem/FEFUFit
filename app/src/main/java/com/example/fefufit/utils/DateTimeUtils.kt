package com.example.fefufit.utils

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDuration(startTime: LocalDateTime, endTime: LocalDateTime): String {
    val duration = Duration.between(startTime, endTime)
    val hours = duration.toHours()
    val minutes = duration.minusHours(hours).toMinutes()

    val hourPart = when (hours) {
        1L -> "1 час"
        in 2..4 -> "$hours часа"
        in 5..23 -> "$hours часов"
        else -> ""
    }

    val minutePart = when (minutes) {
        1L -> "1 минута"
        in 2..4 -> "$minutes минуты"
        in 5..59 -> "$minutes минут"
        else -> ""
    }

    return listOf(hourPart, minutePart).filter { it.isNotEmpty() }.joinToString(" ")
}

fun formatTimeFinishAgo(endTime: LocalDateTime): String {
    val now = LocalDateTime.now()
    val duration = Duration.between(endTime, now)

    return when {
        duration.toMinutes() < 60 -> {
            val minuteStr = when (val minutes = duration.toMinutes()) {
                1L -> "1 минуту назад"
                in 2..4 -> "$minutes минуты назад"
                in 5..59 -> "$minutes минут назад"
                else -> "только что"
            }
            minuteStr
        }

        duration.toHours() < 24 -> {
            val hours = duration.toHours()
            val hourStr = when (hours) {
                1L -> "1 час назад"
                in 2..4 -> "$hours часа назад"
                else -> "$hours часов назад"
            }
            hourStr
        }

        else -> {
            val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
            endTime.format(formatter)
        }
    }
}