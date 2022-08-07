package com.example.sobok_android.domain.model.pill.pilladd

data class PillInfo(
    val pillName: String,
    val isStop: Boolean,
    val color: String,
    val start: String, // Date로 수정해야함
    val end: String, // Date로 수정해야함
    val cycle: Int,
    val day: String?,
    val specific: String?,
    val timeList: List<String>
)
