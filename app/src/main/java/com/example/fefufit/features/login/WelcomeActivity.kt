package com.example.fefufit.features.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fefufit.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private var _binding: ActivityWelcomeBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("ActivityWelcomeBinding is null")

    private fun setButtonsOnClickListeners() {
        val intentRegistration = Intent(this@WelcomeActivity, RegistrationActivity::class.java)
        val intentLogin = Intent(this@WelcomeActivity, LoginActivity::class.java)

        with(binding) {
            btnRegistration.setOnClickListener {
                startActivity(intentRegistration)
            }
            btnLogin.setOnClickListener {
                startActivity(intentLogin)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonsOnClickListeners()
    }
}