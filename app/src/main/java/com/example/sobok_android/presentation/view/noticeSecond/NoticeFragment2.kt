package com.example.sobok_android.presentation.view.noticeSecond

import android.os.Bundle
import android.view.View
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentNotice2Binding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.noticeSecond.model.NoticeData


class NoticeFragment2 : BindingFragment<FragmentNotice2Binding>(R.layout.fragment_notice2) {
    private lateinit var noticeListAdapter2: NoticeListAdapter2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNoticeListAdapter()

    }

    private fun initNoticeListAdapter() {
        noticeListAdapter2 = NoticeListAdapter2()

        binding.rvNotice.adapter = noticeListAdapter2

        noticeListAdapter2.noticeList2.addAll(
            listOf(
                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
                    NoticeData.Data.Info(44, "pill", "waiting", true, "오후 12:35", "cryon", "정관장", 453)
                )),
                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
                    NoticeData.Data.Info(45, "calendar", "refuse", true, "오후 11:35", "garden", "", 453)
                )),
                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
                    NoticeData.Data.Info(45, "calendar", "accept", true, "오후 11:37", "garden", "", 453)
                )),
                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
                    NoticeData.Data.Info(46, "pill", "accept", true, "오후 12:30", "abc", "루테인", 453)
                )),
                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
                    NoticeData.Data.Info(47, "pill", "accept", true, "오후 12:35", "lou", "임상실험약", 453)
                )),
                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
                    NoticeData.Data.Info(48, "calendar", "waiting", true, "오후 12:37", "yayayay", "", 453)
                )),
                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
                    NoticeData.Data.Info(49, "pill", "refuse", true, "오후 12:35", "choi", "홍상", 453)
                )),
            )
        )

        if (noticeListAdapter2.noticeList2.isEmpty()) {
            binding.isEmpty = true
        } else false

        noticeListAdapter2.notifyDataSetChanged()
    }





}