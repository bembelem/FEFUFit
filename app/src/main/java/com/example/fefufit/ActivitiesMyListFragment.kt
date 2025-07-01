package com.example.fefufit
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fefufit.databinding.FragmentActivitiesListBinding

class ActivitiesMyListFragment: Fragment() {
    private var _binding: FragmentActivitiesListBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("FragmentActivitiesListBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivitiesListBinding.inflate(inflater, container, false)

        return binding.root
    }
}