package com.example.sobok_android.presentation.view.home

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentHomeBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.home.viewmodel.HomeViewModel
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homePillListAllAdapter: HomePillListAllAdapter

    // 고차함수써보기
    // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
    private val mainViewModel : MainViewModel by sharedViewModel()

    // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
    private var _isEdit : Boolean = true
    private val homeViewModel : HomeViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observeIsEditClickEvent()



    }


    private fun initAdapter(){
        homePillListAllAdapter = HomePillListAllAdapter()

        binding.rvHomePillListAll.adapter = homePillListAllAdapter

        // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
       homePillListAllAdapter.setStickerClickListener {
            mainViewModel.setIsStickerClick(it)
            Log.d("lala", "success${it}")
        }



        homePillListAllAdapter.homePillListAll =
            listOf(
                HomePillListData("7시",
                    listOf(
                        HomePillListData.PillDetailData("홍삼", true, "1"),
                        HomePillListData.PillDetailData("홍상2", false,"2"),
                        HomePillListData.PillDetailData("홍상3", false,"3"),)),
                HomePillListData("12시",
                    listOf(
                        HomePillListData.PillDetailData("텐텐", false,"4"),
                        HomePillListData.PillDetailData("텐텐3", true,"5"),)),
                HomePillListData("18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false,"1"),
                        HomePillListData.PillDetailData("비타민2", false,"3"),
                        HomePillListData.PillDetailData("텐텐2", true,"4"),
                        HomePillListData.PillDetailData("비타민3", true,"2"),)),
                HomePillListData("18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false,"5"),
                        HomePillListData.PillDetailData("비타민3", true,"2"),)),
                HomePillListData("18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false,"1"),
                        HomePillListData.PillDetailData("비타민2", false,"3"),
                        HomePillListData.PillDetailData("비타민3", true,"2"),)),
                HomePillListData("18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false,"4"),
                        HomePillListData.PillDetailData("비타민2", false,"5"),
                        HomePillListData.PillDetailData("비타민3", true,"2"),)),
            )
        binding.isEdit = true

        // 메인 약 리스트 수정버튼<->완료 버튼
//        binding.tvBtnHomePillListEdit.setOnClickListener {
//            binding.isEdit = !binding.isEdit!!
//        }







    }

    // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
    private fun observeIsEditClickEvent() {
        homeViewModel.isEditClick.observe(this) {
            homePillListAllAdapter.setIsEdit(_isEdit)
        }


    }






}