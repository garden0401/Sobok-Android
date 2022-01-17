package com.example.sobok_android.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.PopupMenu
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityMainBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.home.HomeStickerBottomSheetAdapter
import com.example.sobok_android.presentation.view.home.HomeStickerBottomSheetFragment
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    // 고차함수 써보기
    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var homeStickerBottomSheet: HomeStickerBottomSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initHomeStickerBottomSheet()
        setNavigation()
        observeIsStickerClickEvent()
//        setPop()

    }

    private fun initHomeStickerBottomSheet() {
        homeStickerBottomSheet = HomeStickerBottomSheetFragment()
        homeStickerBottomSheet.setStickerBottomSheetCloseClickListener {
            mainViewModel.setIsStickerClick(it)
        }
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bnvMain.setupWithNavController(navController)
    }

    private fun observeIsStickerClickEvent() {
        mainViewModel.isStickerClick.observe(this) {
            Log.d("checkobserve", "${it}")
            if (it) {
                homeStickerBottomSheet.show(supportFragmentManager, homeStickerBottomSheet.tag)

            }
        }


    }

//    // 약 리스트 수정 클릭시 팝업(임시로 홈프래그먼트에 띄워보기)
//    private fun initSetPopup() {
//        var popup = PopupMenu(this, findViewById(R.id.iv_home_pill_list_edit))
//        menuInflater?.inflate(R.menu.popup_home_pill_list_edit, popup.menu)
//
//        popup.setOnMenuItemClickListener {
//            when (it?.itemId) {
//                R.id.pill_edit -> {
//                    // 다이얼로그
//                    return@setOnMenuItemClickListener true
//                }
//                R.id.pill_delete -> {
//                    return@setOnMenuItemClickListener true
//                }
//                R.id.pill_stop -> {
//                    return@setOnMenuItemClickListener true
//
//                }
//            }
//            return@setOnMenuItemClickListener false
//        }
//        popup.show()
//    }

}