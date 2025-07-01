package com.example.fefufit.features.activities_list
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

class ActivitiesMyListFragment: ActivitiesBaseListFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.adapter = ActivityAdapter(true) { activityId ->
            super.onActivityClick(activityId)
        }

        super.binding.rvActivitiesList.layoutManager = LinearLayoutManager(requireContext())
        super.binding.rvActivitiesList.adapter = super.adapter

        activityViewModel.getAllActivityEntries().observe(viewLifecycleOwner) { activities ->
            adapter.updateItems(activities)
        }
    }
}