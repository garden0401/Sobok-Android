package com.example.sobok_android.util

import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


object DateTimeUtil {
    private val simpleDateFormat = SimpleDateFormat("M월 d일 E요일", Locale.KOREA)
    private val simpleDashDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    private val simpleDateTypeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA)
    private val simpleDateDetailFormat = SimpleDateFormat("yyyy-MM-dd EE HH:mm:ss", Locale.KOREA)
    private val simpleToBeFamilyDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    private val simpleStoryDateFormat = SimpleDateFormat("yyyy년 M월의 이야기", Locale.KOREA)
    private val remoteDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA)
    private val simpleUSFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.US)
    private val dayOfWeekFormat = SimpleDateFormat("E", Locale.KOREA)
    private val dayOfMonthFormat = SimpleDateFormat("dd", Locale.KOREA)

    //2022-01-15 토 22:11:59
    //2021-12-15T00:00:00.000Z

    //Sat Jan 22 00:00:00 GMT 2022
    //simpleUsFormat

    fun isSameDate(usDate: Date, comDate: Date) : Boolean {
        return simpleDashDateFormat.format(usDate).toString() == simpleDashDateFormat.format(comDate).toString()
    }

    fun convertDateToSimpleDateFormat(calendar: Calendar): String {
        return simpleDateFormat.format(calendar.time)
    }

    fun convertCalendarToSimpleDateDetailFormat(calendar: Calendar): String {
        return simpleDateDetailFormat.format(calendar.time)
    }

    fun convertSimpleDetailDateFormatToSimpleDateFormat(str: String) : String {
        return simpleDateFormat.format(simpleDateDetailFormat.parse(str).time)
    }

    fun convertDateToDayOfWeek(date: Date) : String {
        return dayOfWeekFormat.format(date)
    }

    fun convertDateToDayOfMonth(date: Date) : Int {
        return dayOfMonthFormat.format(date).toInt()
    }

    fun convertIntToDayOfWeek(value: Int) : String {
        return when(value) {
            1 -> "일"
            2 -> "월"
            3 -> "화"
            4 -> "수"
            5 -> "목"
            6 -> "금"
            else -> "토"
        }
    }

    fun convertUSDateToDashFormatString(date: Date) : String {
        return simpleDashDateFormat.format(date).toString()
    }

}