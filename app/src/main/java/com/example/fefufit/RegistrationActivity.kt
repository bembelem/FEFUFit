package com.example.fefufit

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fefufit.databinding.ActivityRegistrationBinding

class RegistrationActivity: AppCompatActivity() {
    private var _binding: ActivityRegistrationBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("ActivityRegistrationBinding is null")

    private fun getSpannableString(
        text: String,
        phrasesList: List<Pair<String, OnClickListener>>,
        phraseColor: Int,
    ): SpannableString {
        val spannableString = SpannableString(text)

        phrasesList.forEach { (phrase, onClickListener) ->
            val clickableSpan = object: ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = phraseColor
                    ds.isUnderlineText = false
                }

                override fun onClick(view: View) {
                    onClickListener.onClick(view)
                }
            }

            val start = text.indexOf(phrase)
            val end = start + phrase.length
            spannableString.setSpan(
                clickableSpan,
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return spannableString
    }

    private fun makeLinks() {
        val privacyPolicyOnClickListener = OnClickListener {
            TODO("Show privacy policy")
        }
        val userAgreementOnClickListener = OnClickListener {
            TODO("Show user agreement")
        }

        val phrasesList = listOf(
            Pair(getString(R.string.link_privacy_policy), privacyPolicyOnClickListener),
            Pair(getString(R.string.link_user_agreement), userAgreementOnClickListener)
        )
        val spannablePrivacyPolicy = getSpannableString(
            getString(R.string.caption_registration),
            phrasesList,
            ContextCompat.getColor(this@RegistrationActivity, R.color.blue),
        )

        with(binding) {
            tvCaptionRegistration.movementMethod = LinkMovementMethod.getInstance()
            tvCaptionRegistration.setText(spannablePrivacyPolicy, TextView.BufferType.SPANNABLE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeLinks()

        binding.icGoBack.setOnClickListener {
            val intent = Intent(this@RegistrationActivity, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }
}