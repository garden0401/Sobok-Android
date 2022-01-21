package com.example.sobok_android.data.mapper

import com.example.sobok_android.data.model.response.pill.pilladd.ResPillCountData
import com.example.sobok_android.data.model.response.share.request.ResSearchResultData
import com.example.sobok_android.data.model.response.share.request.ResShareRequestSuccessData
import com.example.sobok_android.domain.model.pill.pilladd.PillCountData
import com.example.sobok_android.domain.model.share.request.SearchResultData
import com.example.sobok_android.domain.model.share.request.ShareRequestSuccessData

object PillAddPossiblePillNumberRequestMapper {

    fun mapperToPillCountData(resPillCountData: ResPillCountData): PillCountData {
        return PillCountData(
            pillCount = resPillCountData.data.pillCount
        )
    }

}