package com.example.sobok_android.data.datasource.notice2

import com.example.sobok_android.data.model.response.notice2.ResNoticeListData2

interface NoticeDataSource2 {
    suspend fun getNoticeList(): ResNoticeListData2
}