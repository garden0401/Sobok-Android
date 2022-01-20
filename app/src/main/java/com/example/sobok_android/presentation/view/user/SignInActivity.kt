package com.example.sobok_android.presentation.view.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivitySignInBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.MainActivity
import com.example.sobok_android.presentation.view.user.request.viewmodel.SignInViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private val signInViewModel : SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnSignUpEvent()
        btnSignInEvent()
        observeSignInSuccess()
    }

    private fun btnSignUpEvent() {
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }
    }

    private fun btnSignInEvent() {
        binding.btnSignIn.setOnClickListener {

            if(binding.etSignInEmail.text.isNotEmpty() && binding.etSignInPw.text.isNotEmpty()) {
                signInViewModel.email = binding.etSignInEmail.text.toString()
                signInViewModel.password = binding.etSignInPw.text.toString()
                signInViewModel.postSignIn()
            }



        }
    }

    private fun observeSignInSuccess() {
        signInViewModel.memberInfo.observe(this) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}