package com.example.sobok_android.data.datasource.pill.pilladd

import com.example.sobok_android.data.api.PillAddService
import com.example.sobok_android.data.model.response.pill.pilladd.ResPillCountData
import com.example.sobok_android.domain.model.pill.pilladd.PillListData

class PillAddRemoteDataSource(private val pillAddService: PillAddService): PillAddDataSource {
    override suspend fun getPillList(): PillListData {
        // 얘는 로컬 리스트로 받아오는데 필요한가????
        // return 이 없어야하는데...
        return getPillList()
    }

    override suspend fun getPillCount(): ResPillCountData {
        return pillAddService.getMyPossiblePillNum()
    }
}