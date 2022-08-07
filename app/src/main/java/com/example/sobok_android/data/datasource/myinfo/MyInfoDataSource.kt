package com.example.sobok_android.data.datasource.myinfo

import com.example.sobok_android.data.model.response.myinfo.ResMyPillData

interface MyInfoDataSource {
    suspend fun getMyPillList(): ResMyPillData
}