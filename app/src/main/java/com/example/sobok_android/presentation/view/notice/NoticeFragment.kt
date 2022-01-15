package com.example.sobok_android.presentation.view.notice

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentNoticeBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.notice.model.NoticeInfoData
import com.example.sobok_android.presentation.view.notice.viewmodel.NoticeViewModel
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NoticeFragment : BindingFragment<FragmentNoticeBinding>(R.layout.fragment_notice) {
    private lateinit var noticeListAdapter: NoticeListAdapter

    private val noticeViewModel: NoticeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initNoticeListAdapter()
        getNoticeList()
        observeNoticeList()

//        mutableListOf(listOf(1,3), listOf(3, 4))

    }

    private fun initNoticeListAdapter(){
        noticeListAdapter = NoticeListAdapter()
        binding.rvNoticeList.adapter = noticeListAdapter
    }

    private fun getNoticeList() {
        noticeViewModel.getNoticeList()
    }

    private fun observeNoticeList() {
        noticeViewModel.noticeListData.observe(viewLifecycleOwner) { it ->
            val noticeInfoList = mutableListOf<NoticeInfoData>()
            noticeInfoList.addAll(it.data.calendarInfo.map{
                NoticeInfoData(
                    isCalendar = true,
                    senderId = it.userId,
                    senderName = it.username,
                    createdAt = it.createdAt,
                    receiverId = 0,
                    receiverName = "null"
                )
            })
            noticeInfoList.addAll(it.data.pillInfo.map {
                NoticeInfoData(
                    isCalendar = false,
                    senderId = it.senderId,
                    senderName = it.senderName,
                    createdAt = it.createdAt,
                    receiverName = it.receiverName,
                    receiverId = it.receiverId
                )
            })
            noticeInfoList.sortBy { it.createdAt }
            Log.d("확인", "haha$noticeInfoList")
            noticeListAdapter.noticeList = noticeInfoList
//            var alist = it.data.calendarInfo + it.data.pillInfo
//            alist.sortedBy {it }
        }
    }


}