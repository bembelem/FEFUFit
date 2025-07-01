package com.example.fefufit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fefufit.databinding.ActivityLoginBinding

class LoginActivity:AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("ActivityLoginBinding is null")

    private fun setButtonsOnClickListeners() {
        with(binding) {
            icGoBack.setOnClickListener {
                val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)
                startActivity(intent)
            }
            btnLogin.setOnClickListener {
                val intent = Intent(this@LoginActivity, ActivityActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonsOnClickListeners()
    }
}