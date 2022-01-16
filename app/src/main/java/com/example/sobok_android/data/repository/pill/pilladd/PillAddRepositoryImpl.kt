package com.example.sobok_android.data.repository.pill.pilladd

import com.example.sobok_android.data.datasource.pill.pilladd.PillAddDataSource
import com.example.sobok_android.domain.model.pill.pilladd.PillListData
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository

class PillAddRepositoryImpl(private val pillAddDataSource : PillAddDataSource) : PillAddRepository{
    override suspend fun getPillList(): PillListData {
        return pillAddDataSource.getPillList()
    }
}