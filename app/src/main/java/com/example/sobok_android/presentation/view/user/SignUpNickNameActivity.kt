package com.example.sobok_android.presentation.view.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivitySignInBinding
import com.example.sobok_android.databinding.ActivitySignUpNickNameBinding
import com.example.sobok_android.presentation.base.BindingActivity

class SignUpNickNameActivity : BindingActivity<ActivitySignUpNickNameBinding>(R.layout.activity_sign_up_nick_name) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnBackSignUpEvent()
        btnSignUpFinishEvent()
    }

    private fun btnBackSignUpEvent() {
        binding.ivBackSignUp.setOnClickListener {
            Log.d("btnBackSignUpEvetn", "success")
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun btnSignUpFinishEvent() {
        binding.btnSignUpFinish.setOnClickListener {
            val intent = Intent(this, SignUpWelcomeActivity::class.java)
            startActivity(intent)
        }
    }
}