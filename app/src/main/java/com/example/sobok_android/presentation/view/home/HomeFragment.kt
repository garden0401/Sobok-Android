package com.example.sobok_android.presentation.view.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentHomeBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homePillListAllAdapter: HomePillListAllAdapter

    private val mainViewModel : MainViewModel by sharedViewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        }

    private fun initAdapter(){
        homePillListAllAdapter = HomePillListAllAdapter()

        binding.rvHomePillListAll.adapter = homePillListAllAdapter

        homePillListAllAdapter.setStickerClickListener {
            mainViewModel.setIsStickerClick(it)
            Log.d("lala", "success${it}")
        }

        homePillListAllAdapter.homePillListAll =
            listOf(
                HomePillListData("7시",
                    listOf(
                        HomePillListData.PillDetailData("홍삼", true, "5"),
                        HomePillListData.PillDetailData("홍상2", false,"6"),
                        HomePillListData.PillDetailData("홍상3", false,"4"),)),
                HomePillListData("12시",
                    listOf(
                        HomePillListData.PillDetailData("텐텐", false,"4"),
                        HomePillListData.PillDetailData("텐텐3", true,"5"),)),
                HomePillListData("18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false,"1"),
                        HomePillListData.PillDetailData("비타민2", false,"3"),
                        HomePillListData.PillDetailData("텐텐2", true,"9"),
                        HomePillListData.PillDetailData("비타민3", true,"8"),)),
                HomePillListData("18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false,"1"),
                        HomePillListData.PillDetailData("비타민3", true,"8"),)),
                HomePillListData("18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false,"1"),
                        HomePillListData.PillDetailData("비타민2", false,"3"),
                        HomePillListData.PillDetailData("비타민3", true,"8"),)),
                HomePillListData("18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false,"1"),
                        HomePillListData.PillDetailData("비타민2", false,"3"),
                        HomePillListData.PillDetailData("비타민3", true,"8"),)),
            )
    }


}