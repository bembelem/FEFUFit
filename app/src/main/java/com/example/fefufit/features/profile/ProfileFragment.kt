package com.example.fefufit.features.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fefufit.R
import com.example.fefufit.databinding.FragmentProfileBinding

class ProfileFragment: Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("FragmentProfileBinding is null")

    companion object {
        const val FRAGMENT_TAG = "ProfileFragment"
    }

    private fun switchToPasswordChange() {
        parentFragmentManager.commit {
            replace(
                R.id.fragmentContainerView,
                PasswordChangeFragment(),
                PasswordChangeFragment.FRAGMENT_TAG
            )
            addToBackStack("switchToPasswordChange")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.linkPasswordChange.setOnClickListener {
            switchToPasswordChange()
        }
    }
}