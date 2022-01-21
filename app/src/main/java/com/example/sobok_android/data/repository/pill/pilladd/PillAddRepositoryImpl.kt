package com.example.sobok_android.data.repository.pill.pilladd

import com.example.sobok_android.data.datasource.pill.pilladd.PillAddDataSource
import com.example.sobok_android.data.mapper.PillAddPossiblePillNumberRequestMapper
import com.example.sobok_android.domain.model.pill.pilladd.PillCountData
import com.example.sobok_android.domain.model.pill.pilladd.PillListData
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import com.example.sobok_android.presentation.view.pill.add.PillAddNavigateData

class PillAddRepositoryImpl(private val pillAddDataSource : PillAddDataSource) : PillAddRepository{
    override suspend fun getPillList(): PillListData {
        return pillAddDataSource.getPillList()
    }

    override suspend fun getPillCount(): PillCountData {
        return PillAddPossiblePillNumberRequestMapper.mapperToPillCountData(pillAddDataSource.getPillCount())
    }
}