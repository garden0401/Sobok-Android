package com.example.sobok_android.domain.repository.notice2

import com.example.sobok_android.domain.model.notice2.NoticeListData2

interface NoticeRepository2 {
    suspend fun getNoticeList() : NoticeListData2
}