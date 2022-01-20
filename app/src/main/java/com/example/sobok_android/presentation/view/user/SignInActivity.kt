package com.example.sobok_android.presentation.view.user

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContentProviderCompat.requireContext
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

//    private fun setDeleteClickListener() {
//        binding.ivTextCloseEmail.setOnClickListener {
//            binding.etSignInEmail.text = null
//        }
//        binding.ivTextClosePw.setOnClickListener {
//            binding.etSignInPw.text = null
//        }
//    }
//
//    private fun hideKeyBoard() {
//        val inputMethodManager: InputMethodManager =
//            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(
//            binding.etSignInEmail.windowToken,
//            0
//        )
//    }
//
//    private fun setEtShareRequestOnFocusChangeListener() {
//        binding.etSignInEmail.setOnFocusChangeListener { view, hasFocus ->
//            if (hasFocus) {
//                binding.isEditable = true
//            } else {
//                binding.isEditable = false
//                hideKeyBoard()
//            }
//        }
//    }
}