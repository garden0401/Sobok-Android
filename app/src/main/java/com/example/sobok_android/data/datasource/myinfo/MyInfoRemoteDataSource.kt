package com.example.sobok_android.data.datasource.myinfo

import com.example.sobok_android.data.api.MyInfoService
import com.example.sobok_android.data.model.request.myinfo.ReqMyNicknameData
import com.example.sobok_android.data.model.response.ResGenericData
import com.example.sobok_android.data.model.response.myinfo.ResMyPillData

class MyInfoRemoteDataSource(private val myInfoService: MyInfoService) : MyInfoDataSource {
    override suspend fun getMyPillList(): ResMyPillData = myInfoService.getMyPillList()
    override suspend fun postUserName(userName: ReqMyNicknameData): ResGenericData =
        myInfoService.postUserName(userName)

    override suspend fun putUserName(userName: ReqMyNicknameData): ResGenericData =
        myInfoService.putUserName(userName)
}