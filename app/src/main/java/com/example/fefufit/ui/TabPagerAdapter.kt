package com.example.fefufit.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fefufit.features.activities_list.ActivitiesMyListFragment
import com.example.fefufit.features.activities_list.ActivitiesUsersListFragment

class TabPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ActivitiesMyListFragment()
            1 -> ActivitiesUsersListFragment()
            else -> ActivitiesMyListFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}