package com.example.fefufit.features.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fefufit.databinding.FragmentPasswordChangeBinding

class PasswordChangeFragment: Fragment() {
    private var _binding: FragmentPasswordChangeBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("FragmentPasswordChangeBinding is null")

    companion object {
        const val FRAGMENT_TAG = "PasswordChangeFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentPasswordChangeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.icGoBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}