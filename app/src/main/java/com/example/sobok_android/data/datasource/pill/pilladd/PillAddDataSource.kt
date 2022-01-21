package com.example.sobok_android.data.datasource.pill.pilladd

import com.example.sobok_android.data.model.response.pill.pilladd.ResPillCountData
import com.example.sobok_android.domain.model.pill.pilladd.PillListData

interface PillAddDataSource {
    suspend fun getPillList() : PillListData

    // 가공이 안된 Data를 받아옴
    suspend fun getPillCount() : ResPillCountData
}