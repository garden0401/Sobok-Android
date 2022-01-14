package com.example.sobok_android.presentation.view

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityMainBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.pill.add.PillAddBottomSheetFragment


class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()

        navigateToPillAdd()
    }

    private fun setNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bnvMain.setupWithNavController(navController)
    }

    private fun navigateToPillAdd() {
        val bottomSheet = PillAddBottomSheetFragment()

        binding.button.setOnClickListener {
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }
}