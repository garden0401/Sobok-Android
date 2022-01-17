package com.example.sobok_android.data.datasource.pill.pilladd

import com.example.sobok_android.domain.model.pill.pilladd.PillListData
import java.util.*

class PillAddLocalDataSource : PillAddDataSource {
    override suspend fun getPillList(): PillListData {
        return PillListData(
            pillList = mutableListOf(
                PillListData.PillInfo(
                    pillName = "비타민",
                    isStop = false,
                    color = "3",
                    start = "2022-01-14",
                    end = "2022-01-21",
                    cycle = "특정 날짜",
                    day = "월, 수",
                    specific = null,
                    timeList = listOf("07:00:00", "10:00:00", "19:00:00")
                ),
                PillListData.PillInfo(
                    pillName = "홍삼",
                    isStop = false,
                    color = "3",
                    start = "2022-01-18",
                    end = "2022-01-21",
                    cycle = "특정 기간",
                    day = null,
                    specific = "1주에 한번",
                    timeList = listOf("07:00:00", "10:00:00", "19:00:00", "21:00:00", "08:00:00", "12:00:00")
                ),
                PillListData.PillInfo(
                    pillName = "오메가3",
                    isStop = false,
                    color = "3",
                    start = "2022-01-11",
                    end = "2022-01-25",
                    cycle = "매일",
                    day = "월, 화, 수, 목, 금",
                    specific = null,
                    timeList = listOf("07:00:00", "21:00:00", "08:00:00", "12:00:00")
                ),
                PillListData.PillInfo(
                    pillName = "프로바이오틱스",
                    isStop = false,
                    color = "3",
                    start = "2022-01-15",
                    end = "2022-01-20",
                    cycle = "특정 기간",
                    day = null,
                    specific = "1주에 한번",
                    timeList = listOf("07:00:00", "10:00:00", "19:00:00", "21:00:00", "08:00:00", "12:00:00")
                )
            )
        )
    }
}