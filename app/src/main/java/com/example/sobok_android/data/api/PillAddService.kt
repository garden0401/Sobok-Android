package com.example.sobok_android.data.api

import com.example.sobok_android.data.model.response.pill.pilladd.ResPillCountData
import retrofit2.http.GET

interface PillAddService {
    // 내약개수
    @GET("pill/count")
    suspend fun getMyPossiblePillNum(
    ): ResPillCountData
}