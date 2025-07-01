package com.example.fefufit.features.activities_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fefufit.R
import com.example.fefufit.databinding.FragmentActivitiesListBinding
import com.example.fefufit.ui.ActivityActivity
import com.example.fefufit.ui.ActivityViewModel

abstract class ActivitiesBaseListFragment: Fragment() {
    private var _binding: FragmentActivitiesListBinding? = null
    protected val binding
        get() = _binding ?: throw IllegalStateException("FragmentActivitiesListBinding is null")
    protected val activityViewModel: ActivityViewModel by activityViewModels {
        (requireActivity() as ActivityActivity).provideActivityViewModelFactory()
    }
    protected lateinit var adapter: ActivityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentActivitiesListBinding.inflate(inflater, container, false)

        return binding.root
    }

    protected fun onActivityClick(activityId: Int) {
        binding.rvActivitiesList.visibility = View.GONE

        val activityDetailsFragment = if (adapter.isMy) ActivityMyDetailsFragment() else ActivityUsersDetailsFragment()

        val bundle = Bundle().apply {
            putInt("activity_id", activityId)
        }
        activityDetailsFragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, activityDetailsFragment)
            .addToBackStack("activityDescription")
            .commit()
    }
}