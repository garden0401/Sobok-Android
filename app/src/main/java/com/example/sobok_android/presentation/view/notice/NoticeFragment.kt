package com.example.sobok_android.presentation.view.notice

import android.os.Bundle
import android.view.View
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentNoticeBinding
import com.example.sobok_android.presentation.base.BindingFragment


class NoticeFragment : BindingFragment<FragmentNoticeBinding>(R.layout.fragment_notice) {
    private lateinit var noticeListAdapter: NoticeListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter(){
        noticeListAdapter = NoticeListAdapter()

        binding.rvNoticeList.adapter = noticeListAdapter

        noticeListAdapter.noticeList =
            listOf(
                NoticeListData("오후 12:35 테스트"),
                NoticeListData("오전 08:35 테스트"),
                NoticeListData("오후 11:35 테스트"),
                NoticeListData("오전 09:35 테스트"),
                NoticeListData("오후 10:35 테스트"),
                NoticeListData("오후 08:35 테스트"),
                NoticeListData("오전 01:35 테스트"),
                NoticeListData("오후 12:39 테스트"),
                NoticeListData("오전 06:35 테스트")
            )

    }
}