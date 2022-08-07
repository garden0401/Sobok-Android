package com.example.sobok_android.data.datasource.myinfo

import com.example.sobok_android.data.model.request.myinfo.ReqMyNicknameData
import com.example.sobok_android.data.model.response.ResGenericData
import com.example.sobok_android.data.model.response.myinfo.ResMyPillData

interface MyInfoDataSource {
    suspend fun getMyPillList(): ResMyPillData
    suspend fun postUserName(userName: ReqMyNicknameData): ResGenericData
    suspend fun putUserName(userName: ReqMyNicknameData): ResGenericData
}