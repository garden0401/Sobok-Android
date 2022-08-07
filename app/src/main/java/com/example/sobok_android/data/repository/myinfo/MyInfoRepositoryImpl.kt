package com.example.sobok_android.data.repository.myinfo

import com.example.sobok_android.data.datasource.myinfo.MyInfoDataSource
import com.example.sobok_android.data.mapper.MyInfoMapper
import com.example.sobok_android.domain.model.myinfo.MyPillData
import com.example.sobok_android.domain.repository.myinfo.MyInfoRepository

class MyInfoRepositoryImpl(private val myInfoDataSource: MyInfoDataSource) : MyInfoRepository {
    override suspend fun getMyPillList(): List<MyPillData> {
        return MyInfoMapper.mapperToMyPillListData(myInfoDataSource.getMyPillList())
    }
}