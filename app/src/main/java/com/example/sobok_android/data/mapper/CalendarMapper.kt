package com.example.sobok_android.data.mapper

import com.example.sobok_android.data.model.response.calendar.ResCalendarData
import com.example.sobok_android.data.model.response.home.ResHomePillListData
import com.example.sobok_android.data.model.response.home.ResSharePillListData
import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.domain.model.home.HomePillListData
import com.example.sobok_android.domain.model.home.PillListData

object CalendarMapper {
    fun mapperToCalendarData(resCalendarData: ResCalendarData): CalendarData {
        return CalendarData(
            data = resCalendarData.data.map {
                CalendarData.CalendarDate(isComplete = it.isComplete, scheduleDate = it.scheduleDate)
            }
        )
    }

    fun mapperToHomePillListDat(resHomePillListData: ResHomePillListData): PillListData {
        return PillListData(
            data = resHomePillListData.data.map {
                PillListData.ScheduleInfo(
                    scheduleTime = it.scheduleTime,
                    scheduleList = it.scheduleList.map {
                        PillListData.ScheduleInfo.Schedule(
                            scheduleId = it.scheduleId,
                            pillId = it.pillId,
                            pillName = it.pillName,
                            scheduleTime = it.scheduleTime,
                            isCheck = it.isCheck,
                            color = it.color,
                            stickerId = it.stickerId.map { PillListData.ScheduleInfo.Schedule.StickerId(
                                likeScheduleId = it.likeScheduleId,
                                stickerId = it.stickerId )
                            },
                            stickerTotalCount = it.stickerTotalCount,
                            isLikedSchedule = false
                        )
                    }
                )
            }
        )
    }

    fun mapperToSharePillListDat(resSharePillListData: ResSharePillListData): PillListData {
        return PillListData(
            data = resSharePillListData.data.map {
               PillListData.ScheduleInfo(
                    scheduleTime = it.scheduleTime,
                    scheduleList = it.scheduleList.map {
                        PillListData.ScheduleInfo.Schedule(
                            scheduleId = it.scheduleId,
                            pillId = it.pillId,
                            pillName = it.pillName,
                            scheduleTime = it.scheduleTime,
                            isCheck = it.isCheck,
                            color = it.color,
                            stickerId = it.stickerId.map {
                                PillListData.ScheduleInfo.Schedule.StickerId(
                                    likeScheduleId = it.likeScheduleId,
                                    stickerId = it.stickerId
                                )
                            },
                            stickerTotalCount = it.stickerTotalCount,
                            isLikedSchedule = it.isLikedSchedule
                        )
                    }
                )
            }
        )
    }
}