package com.example.fefufit

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

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