package com.example.sobok_android.domain.repository.myinfo

import com.example.sobok_android.data.model.request.myinfo.ReqMyNicknameData
import com.example.sobok_android.domain.model.GenericData
import com.example.sobok_android.domain.model.myinfo.MyPillData

interface MyInfoRepository {
    suspend fun getMyPillList(): List<MyPillData>
    suspend fun postUserName(userName: ReqMyNicknameData): GenericData
    suspend fun putUserName(userName: ReqMyNicknameData): GenericData
}