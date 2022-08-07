package com.example.sobok_android.data.repository.myinfo

import com.example.sobok_android.data.datasource.myinfo.MyInfoDataSource
import com.example.sobok_android.data.mapper.MyInfoMapper
import com.example.sobok_android.data.model.request.myinfo.ReqMyNicknameData
import com.example.sobok_android.domain.model.GenericData
import com.example.sobok_android.domain.model.myinfo.MyPillData
import com.example.sobok_android.domain.repository.myinfo.MyInfoRepository

class MyInfoRepositoryImpl(private val myInfoDataSource: MyInfoDataSource) : MyInfoRepository {
    override suspend fun getMyPillList(): List<MyPillData> {
        return MyInfoMapper.mapperToMyPillListData(myInfoDataSource.getMyPillList())
    }

    //TODO: generic type 으로 변경
    override suspend fun postUserName(userName: ReqMyNicknameData): GenericData {
        return with(myInfoDataSource.postUserName(userName)) {
            GenericData(
                this.status,
                this.success,
                this.message
            )
        }
    }

    //TODO: generic type 으로 변경
    override suspend fun putUserName(userName: ReqMyNicknameData): GenericData {
        return with(myInfoDataSource.putUserName(userName)) {
            GenericData(
                this.status,
                this.success,
                this.message
            )
        }
    }
}