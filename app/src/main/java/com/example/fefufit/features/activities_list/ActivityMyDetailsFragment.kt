package com.example.fefufit.features.activities_list

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams

class ActivityMyDetailsFragment: ActivityDetailsFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with (super.binding) {
            icDelete.setOnClickListener {
                if (activityId != null) {
                    activityViewModel.deleteActivityById(activityId!!)
                }
                parentFragmentManager.popBackStack()
            }

            tvUser.visibility = View.GONE

            val params = tvDistance.layoutParams as MarginLayoutParams
            params.topMargin = 0
            tvDistance.layoutParams = params
        }
    }
}