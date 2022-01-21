package com.example.sobok_android.presentation.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.sobok_android.R
import com.example.sobok_android.presentation.view.user.SignInActivity
import com.example.sobok_android.presentation.view.user.SignUpActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplash()
        setContentView(R.layout.activity_splash)
    }

    private fun initSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, SignInActivity::class.java))
            finish()
        }, SPLASH_VIEW_TIME)
    }

    companion object {
        const val SPLASH_VIEW_TIME : Long = 3000
    }

}