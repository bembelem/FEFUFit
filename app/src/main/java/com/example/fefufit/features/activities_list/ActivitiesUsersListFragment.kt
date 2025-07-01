package com.example.fefufit.features.activities_list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

class ActivitiesUsersListFragment: ActivitiesBaseListFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = listOf(
            ActivityItem.DateHeader("Вчера"),
            ActivityItem.ActivityEntry(
                1,
                "Серфинг",
                "14.32 км",
                "14 часов назад",
                "10:00",
                "12:46",
                "2 часа 46 минут",
                "@van_darkholme"
            ),
            ActivityItem.ActivityEntry(
                2,
                "Качели",
                "228 м",
                "14 часов назад",
                "6:00",
                "20:48",
                "14 часов 48 минут",
                "@techniquepasha"
            ),
            ActivityItem.ActivityEntry(
                3,
                "Езда на кадилак",
                "10 км",
                "14 часов назад",
                "22:00",
                "23:10",
                "1 час 10 минут",
                "@morgen_shtern"
            )
        )

        super.adapter = ActivityAdapter(false, data) { activityId ->
            super.onActivityClick(activityId)
        }

        super.binding.rvActivitiesList.layoutManager = LinearLayoutManager(requireContext())
        super.binding.rvActivitiesList.adapter = super.adapter
    }
}