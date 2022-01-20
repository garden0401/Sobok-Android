package com.example.sobok_android.presentation.view.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivitySignUpNickNameBinding
import com.example.sobok_android.databinding.ActivitySignUpWelcomeBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.MainActivity

class SignUpWelcomeActivity : BindingActivity<ActivitySignUpWelcomeBinding>(R.layout.activity_sign_up_welcome) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnAppStart()
    }

    private fun btnAppStart() {
        binding.btnAppStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}