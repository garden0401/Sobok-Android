package com.example.sobok_android.domain.repository

import com.example.sobok_android.data.model.response.notice.ResNoticeListData
import com.example.sobok_android.domain.model.notice.NoticeListData

interface NoticeRepository {
    suspend fun getNoticeList(): NoticeListData
}