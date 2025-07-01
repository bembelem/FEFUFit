package com.example.fefufit.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fefufit.data.db.dao.ActivityDao
import com.example.fefufit.data.model.UserActivity

@Database(entities = [UserActivity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun activityDao(): ActivityDao
}