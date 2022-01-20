package com.example.sobok_android.presentation.view.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivitySignUpBinding
import com.example.sobok_android.presentation.base.BindingActivity
import org.koin.android.ext.android.bind

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnBackSignInEvent()
        btnConfirmEvent()
    }

    private fun btnBackSignInEvent() {
        binding.ivBackSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun btnConfirmEvent() {
        binding.btnConfirm.setOnClickListener {
            val intent = Intent(this, SignUpNickNameActivity::class.java)
            startActivity(intent)
        }
    }
}