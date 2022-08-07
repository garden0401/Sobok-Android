package com.example.sobok_android.data.mapper

import com.example.sobok_android.data.model.response.myinfo.ResMyPillData
import com.example.sobok_android.domain.model.myinfo.MyPillData

object MyInfoMapper {
    fun mapperToMyPillListData(resMyPillData: ResMyPillData): List<MyPillData> {
        return resMyPillData.data.map {
            MyPillData(
                pillId = it.id,
                pillName = it.pillName,
                color = it.color
            )
        }
    }
}