package com.example.sobok_android.domain.repository.pill.pilladd

import com.example.sobok_android.data.datasource.pill.pilladd.PillAddDataSource
import com.example.sobok_android.data.repository.pill.pilladd.PillAddRepositoryImpl
import com.example.sobok_android.domain.model.pill.pilladd.PillCountData
import com.example.sobok_android.domain.model.pill.pilladd.PillListData
import com.example.sobok_android.presentation.view.pill.add.PillAddNavigateData

interface PillAddRepository {

    suspend fun getPillList() : PillListData

    suspend fun getPillCount() : PillCountData
}