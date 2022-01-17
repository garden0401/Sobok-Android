package com.example.sobok_android.presentation.view.home

data class HomePillListData(
    val scheduleTime : String,
    val scheduleList: List<PillDetailData>
) {
    data class PillDetailData(
        val pillName : String,
        val isCheck : Boolean,
//        val stickerImg : List<String>,
        val color : String,
//        val stickerCount : String
    )
}
