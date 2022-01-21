package com.example.sobok_android.data.mapper

import com.example.sobok_android.data.model.response.share.ResGroupData
import com.example.sobok_android.data.model.response.share.request.ResSearchResultData
import com.example.sobok_android.data.model.response.share.request.ResShareRequestSuccessData
import com.example.sobok_android.domain.model.share.request.GroupData
import com.example.sobok_android.domain.model.share.request.SearchResultData
import com.example.sobok_android.domain.model.share.request.ShareRequestSuccessData

object ShareRequestMapper {
    fun mapperToSearchResultData(resSearchResultData: ResSearchResultData): SearchResultData {
        return SearchResultData(
            data = resSearchResultData.data.map { SearchResultData.Member(it.memberId, it.memberName) }
        )
    }

    fun mapperToSearchResultSuccessData(resShareRequestSuccessData: ResShareRequestSuccessData): ShareRequestSuccessData {
        return ShareRequestSuccessData(
            data = ShareRequestSuccessData.MemberInfo(resShareRequestSuccessData.data.memberId, resShareRequestSuccessData.data.memberName)
        )
    }

    fun mapperToGroupData(resGroupData: ResGroupData) : GroupData {
        return GroupData(resGroupData.data.map{ GroupData.MemberInfo(it.memberId, it.memberName)})
    }

}