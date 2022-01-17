package com.example.sobok_android.data.mapper

import com.example.sobok_android.data.model.response.share.request.ResSearchResultData
import com.example.sobok_android.domain.model.share.request.SearchResultData

object ShareRequestMapper {
    fun mapperToSearchResultData(resSearchResultData: ResSearchResultData): SearchResultData {
        return SearchResultData(
            data = resSearchResultData.data.map { SearchResultData.Member(it.memberId, it.memberName) }
        )
    }

}