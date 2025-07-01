package com.example.fefufit.features.activities_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fefufit.databinding.FragmentActivityDetailsBinding
import com.example.fefufit.ui.ActivityActivity
import com.example.fefufit.ui.ActivityViewModel

abstract class ActivityDetailsFragment: Fragment() {
    private var _binding: FragmentActivityDetailsBinding? = null
    protected val binding
        get() = _binding ?: throw IllegalStateException("FragmentActivityDetailsBinding is null")
    protected var activityId: Int? = null
    protected val activityViewModel: ActivityViewModel by activityViewModels {
        (requireActivity() as ActivityActivity).provideActivityViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentActivityDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activityId = arguments?.getInt("activity_id") ?: return
        this.activityId = activityId

        with(binding) {
            icGoBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            activityViewModel.getActivityEntryById(activityId)
                .observe(viewLifecycleOwner) { activity ->

                    tvActivityName.text = activity.activityName
                    tvUser.text = activity.user
                    tvDistance.text = activity.distance
                    tvTimeFinishAgo.text = activity.timeFinishAgo
                    tvTimeDuration.text = activity.timeDuration
                    tvTimeStart.text = activity.timeStart
                    tvTimeFinish.text = activity.timeFinish

                    tfComment.setText(activity.comment)
                }
        }
    }
}