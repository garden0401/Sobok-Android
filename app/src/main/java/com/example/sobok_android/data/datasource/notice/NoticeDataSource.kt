package com.example.sobok_android.data.datasource.notice

import com.example.sobok_android.data.model.response.notice.ResNoticeListData

interface NoticeDataSource {
    suspend fun getNoticeList(): ResNoticeListData
}