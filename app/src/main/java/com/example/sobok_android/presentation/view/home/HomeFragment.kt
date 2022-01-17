package com.example.sobok_android.presentation.view.home

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentHomeBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homePillListAllAdapter: HomePillListAllAdapter

    // 고차함수써보기
    private val mainViewModel : MainViewModel by sharedViewModel()
    private lateinit var editView : View


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

        // 홈(메인) 약 리스트 수정시 context 버튼 클릭-popup menu 띄우기 (고차함수 써보기)
        homePillListAllAdapter.setEditContextClickListener { click, view ->
            mainViewModel.setIsEditContextClick(click)
            Log.d("popupHome", "success${view}")
            editView = view

        }

        // 홈(메인) 약 리스트 수정시 context 버튼 클릭-popup menu 띄우기 (고차함수 써보기)
        mainViewModel.isEditContextClick.observe(this) {
            if (it && ::editView.isInitialized) {
                Log.d("popup", "success")
                initSetPopup()
            }

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
        binding.isEdit = true

        // 메인 약 리스트 수정버튼<->완료 버튼
        binding.tvBtnHomePillListEdit.setOnClickListener {
            binding.isEdit = !binding.isEdit!!
        }


    }


    // 홈(메인) 약 리스트 수정시 context 버튼 클릭-popup menu 띄우기 (고차함수 써보기)
    private fun initSetPopup() {
        var popup = PopupMenu(requireContext(), editView)
        popup.menuInflater?.inflate(R.menu.popup_home_pill_list_edit, popup.menu)

        popup.setOnMenuItemClickListener {
            when (it?.itemId) {
                R.id.pill_edit -> {
                    // 다이얼로그
                    return@setOnMenuItemClickListener true
                }
                R.id.pill_delete -> {
                    return@setOnMenuItemClickListener true
                }
                R.id.pill_stop -> {
                    return@setOnMenuItemClickListener true

                }
            }
            return@setOnMenuItemClickListener false
        }
        popup.show()
    }



}