package com.example.sobok_android.presentation.view.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivitySignInBinding
import com.example.sobok_android.databinding.ActivitySignUpNickNameBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.user.request.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpNickNameActivity : BindingActivity<ActivitySignUpNickNameBinding>(R.layout.activity_sign_up_nick_name) {

    private val signUpViewModel : SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnBackSignUpEvent()
        btnSignUpFinishEvent()
        observeSignUpSuccess()
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
            if(binding.etSignUpNick.text.isNotEmpty()) {
                signUpViewModel.email = intent.getStringExtra("email").toString()
                signUpViewModel.password = intent.getStringExtra("password").toString()
                signUpViewModel.name = binding.etSignUpNick.text.toString()
                if(signUpViewModel.email.isNotEmpty() && signUpViewModel.password.isNotEmpty() && signUpViewModel.name.isNotEmpty() ) {
                    signUpViewModel.postSignUp()
                }
            }

        }
    }

    private fun observeSignUpSuccess() {
        signUpViewModel.memberInfo.observe(this) {
            val intent = Intent(this, SignUpWelcomeActivity::class.java)
            startActivity(intent)
        }
    }
}

