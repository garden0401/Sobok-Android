package com.example.sobok_android.domain.repository.myinfo

import com.example.sobok_android.domain.model.myinfo.MyPillData

interface MyInfoRepository {
    suspend fun getMyPillList(): List<MyPillData>
}