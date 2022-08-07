package com.example.sobok_android.presentation.view.notice2

import android.os.Bundle
import android.view.View
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentNotice2Binding
import com.example.sobok_android.domain.model.notice2.NoticeListData2
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.notice2.model.NoticeData
import com.example.sobok_android.presentation.view.notice2.viewmodel.NoticeViewModel2
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NoticeFragment2 : BindingFragment<FragmentNotice2Binding>(R.layout.fragment_notice2) {
    private lateinit var noticeListAdapter2: NoticeListAdapter2

    private val noticeViewModel2: NoticeViewModel2 by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNoticeListAdapter()
        getNoticeList()
        observeNoticeList()

    }

    private fun initNoticeListAdapter() {
        noticeListAdapter2 = NoticeListAdapter2()
        binding.rvNotice.adapter = noticeListAdapter2

//        noticeListAdapter2.noticeList2.addAll(
//            listOf(
//                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
//                    NoticeData.Data.Info(44, "pill", "waiting", true, "오후 12:35", "cryon", "정관장", 453)
//                )),
//                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
//                    NoticeData.Data.Info(45, "calendar", "refuse", true, "오후 11:35", "garden", "", 453)
//                )),
//                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
//                    NoticeData.Data.Info(45, "calendar", "accept", true, "오후 11:37", "garden", "", 453)
//                )),
//                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
//                    NoticeData.Data.Info(46, "pill", "accept", true, "오후 12:30", "abc", "루테인", 453)
//                )),
//                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
//                    NoticeData.Data.Info(47, "pill", "accept", true, "오후 12:35", "lou", "임상실험약", 453)
//                )),
//                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
//                    NoticeData.Data.Info(48, "calendar", "waiting", true, "오후 12:37", "yayayay", "", 453)
//                )),
//                NoticeData(200, true, "알림리스트 조회 성공", NoticeData.Data(
//                    NoticeData.Data.Info(49, "pill", "refuse", true, "오후 12:35", "choi", "홍상", 453)
//                )),
//            )
//        )

//        if (noticeListAdapter2.noticeList2.isEmpty()) {
//            binding.isEmpty = true
//        } else false
//
//        noticeListAdapter2.notifyDataSetChanged()
    }

    private fun getNoticeList() {
        noticeViewModel2.getNoticeList()
    }

    private fun observeNoticeList() {
        noticeViewModel2.noticeListData2.observe(viewLifecycleOwner) { it ->
            val noticeList = mutableListOf<NoticeListData2.Data.Info>()
            noticeList.addAll(it.data.infoList)

            noticeListAdapter2.noticeList2 = noticeList

//            val userName = mutableListOf<NoticeListData2.Data>()
//            userName.add(it.data)
//            binding.userName = userName

            binding.userName = "소중한 " + it.data.username + "님의 알림"

            if (noticeListAdapter2.noticeList2.isEmpty()) {
                binding.isEmpty = true
            } else false


        }
    }





}