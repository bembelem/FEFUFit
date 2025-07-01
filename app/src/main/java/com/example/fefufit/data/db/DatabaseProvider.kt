package com.example.fefufit.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fefufit.data.enums.ActivityType
import com.example.fefufit.data.model.UserActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.Month

object DatabaseProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "user_activity_db"
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        INSTANCE?.activityDao()?.let { dao ->
                            dao.insertActivity(
                                UserActivity(
                                    type = ActivityType.BICYCLE,
                                    startTime = LocalDateTime.now().minusHours(2),
                                    endTime = LocalDateTime.now(),
                                    distance = 14320
                                )
                            )
                            dao.insertActivity(
                                UserActivity(
                                    type = ActivityType.RUN,
                                    startTime = LocalDateTime.of(2022, Month.MAY, 29, 18, 20),
                                    endTime = LocalDateTime.of(2022, Month.MAY, 29, 19, 20),
                                    distance = 1000
                                )
                            )
                        }
                    }
                }
            }).build().also { INSTANCE = it }
        }
    }
}