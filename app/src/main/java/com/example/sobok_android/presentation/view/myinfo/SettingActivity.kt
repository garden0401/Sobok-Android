package com.example.sobok_android.presentation.view.myinfo

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivitySettingBinding
import com.example.sobok_android.presentation.base.BindingActivity

class SettingActivity : BindingActivity<ActivitySettingBinding>(R.layout.activity_setting) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTopBar()
        initClickEvent()
    }

    private fun initTopBar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_setting) as NavHostFragment
        navController = navHostFragment.navController
        with(binding) {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                fragmentTitle = when (destination.id) {
                    R.id.settingMainFragment -> {
                        getString(R.string.setting)
                    }
                    R.id.settingTermsOfUseFragment -> {
                        getString(R.string.terms_of_use)
                    }
                    else -> {
                        getString(R.string.cancel_membership)
                    }
                }
            }
        }
    }

    private fun initClickEvent() {
        with(binding) {
            ibBack.setOnClickListener {
                if(!navController.popBackStack()) {
                    finish()
                }
            }
        }
    }
}