package com.example.sobok_android.data.datasource.pill.pilladd

import com.example.sobok_android.domain.model.pill.pilladd.PillListData

interface PillAddDataSource {
    suspend fun getPillList() : PillListData
}