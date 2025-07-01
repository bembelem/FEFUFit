package com.example.fefufit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.fefufit.R
import com.example.fefufit.data.db.DatabaseProvider
import com.example.fefufit.data.repository.ActivityRepository
import com.example.fefufit.databinding.ActivityActivityBinding
import com.example.fefufit.features.activities_list.ActivityFragment
import com.example.fefufit.features.profile.ProfileFragment


class ActivityActivity: AppCompatActivity() {
    private var _binding: ActivityActivityBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("ActivityActivityBinding is null")

    private val _activityViewModelFactory: ViewModelProvider.Factory by lazy {
        val activityDao = DatabaseProvider.getDatabase(applicationContext).activityDao()
        val repository = ActivityRepository(activityDao)

        viewModelFactory {
            initializer {
                ActivityViewModel(repository)
            }
        }
    }

    fun provideActivityViewModelFactory(): ViewModelProvider.Factory = _activityViewModelFactory

    private fun switchToProfileFragment() {
        val activityFragment = supportFragmentManager.findFragmentByTag(ActivityFragment.FRAGMENT_TAG)
        val profileFragment = supportFragmentManager.findFragmentByTag(ProfileFragment.FRAGMENT_TAG)

        supportFragmentManager.commit {
            if (activityFragment != null) {
                hide(activityFragment)
            }

            if (profileFragment != null) {
                show(profileFragment)
            } else {
                add(
                    R.id.fragmentContainerView, ProfileFragment(),
                    ProfileFragment.FRAGMENT_TAG
                )
            }
        }
    }

    private fun switchToActivityFragment() {
        val activityFragment = supportFragmentManager.findFragmentByTag(ActivityFragment.FRAGMENT_TAG)
        val profileFragment = supportFragmentManager.findFragmentByTag(ProfileFragment.FRAGMENT_TAG)

        supportFragmentManager.commit {
            if (profileFragment != null) {
                hide(profileFragment)
            }

            if (activityFragment != null) {
                show(activityFragment)
            } else {
                add(
                    R.id.fragmentContainerView, ActivityFragment(),
                    ActivityFragment.FRAGMENT_TAG
                )
            }
        }
    }

    private fun customizeBottomView(savedInstanceState: Bundle?) {
        with(binding) {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .add(
                        R.id.fragmentContainerView,  ActivityFragment(),
                        ActivityFragment.FRAGMENT_TAG
                    )
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