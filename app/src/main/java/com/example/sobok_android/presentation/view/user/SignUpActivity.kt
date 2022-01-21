package com.example.sobok_android.presentation.view.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivitySignUpBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.MainActivity
import com.example.sobok_android.presentation.view.user.request.viewmodel.SignInViewModel
import com.example.sobok_android.presentation.view.user.request.viewmodel.SignUpViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

//    private val signUpViewModel : SignUpViewModel by viewModel()

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
            if(binding.etSignUpEmail.text.isNotEmpty() && binding.etSignUpPw.text.isNotEmpty() && binding.etSignUpPwCheck.text.isNotEmpty()) {
                val email = binding.etSignUpEmail.text.toString()
                val password = binding.etSignUpPw.text.toString()

                val intent = Intent(this, SignUpNickNameActivity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("password", password)

                startActivity(intent)
            }
        }
    }

//    private fun observeSignInSuccess() {
//        signUpViewModel.memberInfo.observe(this) {
//            val intent = Intent(this, SignUpNickNameActivity::class.java)
//            startActivity(intent)
//            startActivity(intent)
//        }
//    }








}