package com.example.fefufit.features.activities_list

sealed class ActivityItem {
    data class DateHeader(val date: String): ActivityItem()
    data class ActivityEntry(
        val id: Int,
        val activityName: String,
        val distance: String,
        val timeFinishAgo: String,
        val timeStart: String,
        val timeFinish: String,
        val timeDuration: String,
        val user: String = "",
        val comment: String = "",
    ): ActivityItem()
}