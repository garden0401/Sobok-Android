package com.example.sobok_android.presentation.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityMainBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.home.HomeFragment
import com.example.sobok_android.presentation.view.home.HomeStickerBottomSheetFragment
import com.example.sobok_android.presentation.view.notice.NoticeFragment
import com.example.sobok_android.presentation.view.pill.add.PillAddActivity
import com.example.sobok_android.presentation.view.pill.add.PillAddBottomSheetFragment
import com.example.sobok_android.presentation.view.share.ShareFragment
import com.example.sobok_android.presentation.view.share.request.ShareRequestActivity
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    // 고차함수 써보기
    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var homeStickerBottomSheet: HomeStickerBottomSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        getGroupData()
        initHomeStickerBottomSheet()
        setNavigation()
        observeIsStickerClickEvent()
        observeIsShareRequest()
        observeIsShareRequestClick()
        observePillAddNavigateData()
    }

    private fun getGroupData() {
        mainViewModel.getGroupData()
    }

    private fun initHomeStickerBottomSheet() {
        homeStickerBottomSheet = HomeStickerBottomSheetFragment()
        homeStickerBottomSheet.setStickerBottomSheetCloseClickListener {
            mainViewModel.setIsStickerClick(it)
        }
    }

    private fun setNavigation() {
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    mainViewModel.setIsHome(true)
                    changeFragment(HomeFragment())
                }
                R.id.shareFragment -> {
                    mainViewModel.setIsHome(false)
                    changeFragment(ShareFragment())
                }
                R.id.noticeFragment -> {
                    changeFragment(NoticeFragment())
                }
                else -> {
                    val pillAddBottomSheetFragment = PillAddBottomSheetFragment()
                    pillAddBottomSheetFragment.show(
                        supportFragmentManager,
                        pillAddBottomSheetFragment.tag
                    )
                }
            }
            true
        }

        binding.bnvMain.selectedItemId = R.id.homeFragment
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fcv_main, fragment).commit()
    }

    private fun observeIsStickerClickEvent() {
        mainViewModel.isStickerClick.observe(this) {
            Log.d("checkobserve", "${it}")
            if (it) {
                homeStickerBottomSheet.show(supportFragmentManager, homeStickerBottomSheet.tag)
            }
        }
    }

    private fun observeIsShareRequest() {
        mainViewModel.isShareRequest.observe(this) {

        }
    }

    private fun observePillAddNavigateData() {
        mainViewModel.pillAddNavigateData.observe(this) {
            val intent = Intent(this, PillAddActivity::class.java)
            // 여기
            intent.putExtra("isMyPill", it.isMyPill)
            intent.putExtra("canAddPill", it.canAddPill)
            intent.putExtra("pillCount", it.pillCount)
            Log.d("observe Log CanAddpill", "${it.canAddPill}")
            Log.d("observe Log IsMyPill", "${it.isMyPill}")
            startActivity(intent)
        }
    }

    private val shareRequestActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            //TODO : 팝업
            if (mainViewModel.isShareRequest.value == true) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("캘린더 공유 요청을 보냈어요!")
                    .setMessage(
                        "상대방이 요청을 수락하면,\n" +
                                "상대방의 캘린더를 볼 수 있어요"
                    )
                    .setNegativeButton("확인") { dialog, id ->
                    }
                builder.show()
            }
        }
    }

    private fun observeIsShareRequestClick() {
        mainViewModel.isShareRequestClick.observe(this) {
            if (it) {
                shareRequestActivityLauncher.launch(Intent(this, ShareRequestActivity::class.java))
            }
        }
    }

}