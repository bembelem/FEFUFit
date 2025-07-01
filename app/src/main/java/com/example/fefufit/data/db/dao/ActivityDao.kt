package com.example.fefufit.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fefufit.data.model.UserActivity

@Dao
interface ActivityDao {
    @Insert
    suspend fun insertActivity(userActivity: UserActivity)

    @Query("""
        SELECT *
        FROM user_activities
        ORDER BY startTime DESC
    """) fun getAllActivities(): LiveData<List<UserActivity>>

    @Query("""
        SELECT *
        FROM user_activities
        WHERE id = :activityId
        LIMIT 1
    """) fun getActivityById(activityId: Int): LiveData<UserActivity>

    @Query("""
        DELETE
        FROM user_activities
        WHERE id = :activityId
    """) suspend fun deleteActivityById(activityId: Int)

}