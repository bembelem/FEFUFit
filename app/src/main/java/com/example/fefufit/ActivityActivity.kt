package com.example.fefufit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.fefufit.databinding.ActivityActivityBinding

object ActivityFragmentsTags {
    const val ACTIVITY_FRAGMENT = "ActivityFragment"
    const val PROFILE_FRAGMENT = "ProfileFragment"
}

class ActivityActivity: AppCompatActivity() {
    private var _binding: ActivityActivityBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("ActivityActivityBinding is null")

    private fun switchToProfileFragment() {
        val activityFragment = supportFragmentManager.findFragmentByTag(ActivityFragmentsTags.ACTIVITY_FRAGMENT)
        val profileFragment = supportFragmentManager.findFragmentByTag(ActivityFragmentsTags.PROFILE_FRAGMENT)

        supportFragmentManager.commit {
            if (activityFragment != null) {
                hide(activityFragment)
            }

            if (profileFragment != null) {
                show(profileFragment)
            } else {
                add(R.id.fragmentContainerView, ProfileFragment(), ActivityFragmentsTags.PROFILE_FRAGMENT)
            }
        }
    }

    private fun switchToActivityFragment() {
        val activityFragment = supportFragmentManager.findFragmentByTag(ActivityFragmentsTags.ACTIVITY_FRAGMENT)
        val profileFragment = supportFragmentManager.findFragmentByTag(ActivityFragmentsTags.PROFILE_FRAGMENT)

        supportFragmentManager.commit {
            if (profileFragment != null) {
                hide(profileFragment)
            }

            if (activityFragment != null) {
                show(activityFragment)
            } else {
                add(R.id.fragmentContainerView, ActivityFragment(), ActivityFragmentsTags.ACTIVITY_FRAGMENT)
            }
        }
    }

    private fun customizeBottomView(savedInstanceState: Bundle?) {
        with(binding) {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainerView,  ActivityFragment(), ActivityFragmentsTags.ACTIVITY_FRAGMENT)
                    .commit()
            }

            bottomNavigationView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_activity -> switchToActivityFragment()
                    R.id.nav_profile -> switchToProfileFragment()
                    else -> switchToActivityFragment()
                }
                true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customizeBottomView(savedInstanceState)
    }
}