package com.example.sobok_android.presentation.view

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityMainBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.pill.add.PillAddBottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()


    }

    private fun setNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bnvMain.setupWithNavController(navController)
    }

    private fun gotoPillAdd() {
        val pillAddBtn = binding.button

        pillAddBtn.setOnClickListener {
            val bottomSheet = PillAddBottomSheetFragment()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }
}