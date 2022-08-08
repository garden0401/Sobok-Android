package com.example.sobok_android.presentation.view.notice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityNoticeDetailPillInfoBinding
import com.example.sobok_android.domain.model.notice2.NoticeDetailData
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.notice2.viewmodel.NoticeViewModel2
import okhttp3.internal.notifyAll
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticeDetailPillInfoActivity : BindingActivity<ActivityNoticeDetailPillInfoBinding>(R.layout.activity_notice_detail_pill_info) {
    private lateinit var noticeDetailAdapter: NoticeDetailAdapter

    private val noticeViewModel2:NoticeViewModel2 by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        btnBackClickEvent()
        initNoticeDetailAdapter()
        getNoticeDetail()
        observeNoticeDetail()
    }


    private fun btnBackClickEvent() {
        binding.btnNoticeDetailBack.setOnClickListener {
            finish()
        }
    }

    private fun initNoticeDetailAdapter() {
        noticeDetailAdapter = NoticeDetailAdapter()
        binding.rvNoticeDetailTimeContent.adapter = noticeDetailAdapter
    }

    private fun getNoticeDetail() {
        val noticeId = intent.getIntExtra("noticeId", -1)
        val pillId = intent.getIntExtra("pillId", -1)
        noticeViewModel2.getNoticeDetail(noticeId, pillId)
    }

    private fun observeNoticeDetail() {

        noticeViewModel2.noticeDetailData.observe(this) {

            noticeDetailAdapter.noticeDetail = it.data.scheduleTime




            binding.noticeDetailData = it

            if (it.data.takeInterval == 3) {
                binding.isSpecific = true
            } else false

            if(it.data.takeInterval == 1) {
                binding.isCycle = "매일"
            } else if(it.data.takeInterval == 2) {
                binding.isCycle = "특정 요일"
            } else binding.isCycle = "특정 간격"

            binding.isPeriod = it.data.startDate + " ~ " + it.data.endDate

        }
    }
}