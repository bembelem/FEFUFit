package com.example.fefufit.features.activities_list

import android.graphics.Paint
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.fefufit.R
import com.google.android.material.textfield.TextInputLayout

class ActivityUsersDetailsFragment: ActivityDetailsFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with (super.binding) {
            icDelete.isVisible = false

            icShare.isVisible = false

            tvUser.paintFlags = Paint.UNDERLINE_TEXT_FLAG

            tilComment.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_NONE
            tilComment.isHintEnabled = false
            tilComment.isFocusable = false
            tilComment.isFocusableInTouchMode = false

            tfComment.isCursorVisible = false
            tfComment.keyListener = null
            tfComment.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
            tfComment.minLines = 1
            tfComment.maxLines = 5
            tfComment.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_grey))
            tfComment.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.background_text_input_grey))
        }
    }
}