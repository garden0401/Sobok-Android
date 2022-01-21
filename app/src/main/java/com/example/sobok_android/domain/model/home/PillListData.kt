package com.example.sobok_android.domain.model.home


data class PillListData(
    val data: List<ScheduleInfo>
) {
    data class ScheduleInfo(
        val scheduleTime: String,
        val scheduleList: List<Schedule>
    ) {
        data class Schedule(
            val scheduleId: Int,
            val pillId: Int,
            val pillName: String,
            val scheduleTime: String,
            val isCheck: Boolean,
            val color: String,
            val stickerId: List<StickerId>,
            val stickerTotalCount: Int,
            val isLikedSchedule: Boolean
        ) {
            data class StickerId(
                val likeScheduleId: Int,
                val stickerId: Int?
            )
        }
    }
}