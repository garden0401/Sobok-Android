package com.example.sobok_android.domain.model.pill.pilladd

import java.io.Serializable

data class PillListData(
    val pillList: MutableList<PillInfo>
) {
    data class PillInfo(
        val pillName: String,
        val isStop: Boolean,
        val color: String,
        val start: String, // Date로 수정해야함
        val end: String, // Date로 수정해야함
        val cycle: String,
        val day: String?,
        val specific: String?,
        val timeList: List<String>
    )
}