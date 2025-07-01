package com.example.fefufit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.fefufit.data.model.UserActivity
import com.example.fefufit.data.repository.ActivityRepository
import com.example.fefufit.features.activities_list.ActivityItem
import com.example.fefufit.utils.formatDuration
import com.example.fefufit.utils.formatTimeFinishAgo
import kotlinx.coroutines.launch

class ActivityViewModel(private val activityRepository: ActivityRepository): ViewModel() {
    private fun UserActivity.toActivityEntry(): ActivityItem.ActivityEntry {
        return ActivityItem.ActivityEntry(
            id,
            type.typeName,
            "$distance м",
            formatTimeFinishAgo(endTime),
            "${startTime.hour}:${startTime.minute}",
            "${endTime.hour}:${endTime.minute}",
            formatDuration(startTime, endTime),
        )
    }

    fun getAllActivityEntries(): LiveData<List<ActivityItem.ActivityEntry>> {
        val userActivities = activityRepository.getAllActivities()
            .map { userActivities ->
                userActivities.map { it.toActivityEntry() }
            }

        return userActivities
    }

    fun getActivityEntryById(activityId: Int): LiveData<ActivityItem.ActivityEntry> {
        val userActivity = activityRepository.getActivityById(activityId)
        return userActivity.map { activity ->
            activity.toActivityEntry()
        }
    }

    fun addActivity(userActivity: UserActivity) {
        viewModelScope.launch {
            activityRepository.insertActivity(userActivity)
        }
    }

    fun deleteActivityById(activityId: Int) {
        viewModelScope.launch {
            activityRepository.deleteActivityById(activityId)
        }
    }
}