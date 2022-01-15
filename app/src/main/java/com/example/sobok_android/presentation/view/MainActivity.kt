package com.example.sobok_android.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityMainBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.home.HomeStickerBottomSheetFragment
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var homeStickerBottomSheet : HomeStickerBottomSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initHomeStickerBottomSheet()
        setNavigation()
        observeIsStickerClickEvent()
    }

    private fun initHomeStickerBottomSheet() {
        homeStickerBottomSheet = HomeStickerBottomSheetFragment()
        homeStickerBottomSheet.setStickerBottomSheetCloseClickListener {
            mainViewModel.setIsStickerClick(it)
        }
    }

    private fun setNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bnvMain.setupWithNavController(navController)
    }

    private fun observeIsStickerClickEvent() {
        mainViewModel.isStickerClick.observe(this) {
            Log.d("checkobserve", "${it}")
            if(it){
                homeStickerBottomSheet.show(supportFragmentManager, homeStickerBottomSheet.tag)
            }
        }

    }
}