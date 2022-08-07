package com.example.sobok_android.presentation.view.pill.add.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sobok_android.domain.model.pill.pilladd.PillInfo
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import com.example.sobok_android.presentation.view.pill.add.PillAddNavigateData

class PillAddViewModel(val pillAddRepository: PillAddRepository) : ViewModel() {

    // repository함수를 불러오면.. data 불러오는거
    private var _pillList: MutableList<PillInfo> = mutableListOf()
    val pillList: MutableList<PillInfo>
        get() = _pillList

    private val _timeList = MutableLiveData<MutableList<String>>()
    val timeList: LiveData<MutableList<String>> = _timeList

    private val _pillTime = MutableLiveData<Int>()
    val pillTime: LiveData<Int> = _pillTime

    private val _pillCycle = MutableLiveData<Int>()
    val pillCycle: LiveData<Int> = _pillCycle

    private var _pillCycleSpecificDaysList = MutableLiveData<MutableList<String>>()
    val pillCycleSpecificDaysList: LiveData<MutableList<String>> = _pillCycleSpecificDaysList

    private var _pillCycleSpecificDaysString = MutableLiveData<String>()
    val pillCycleSpecificDaysString: LiveData<String> = _pillCycleSpecificDaysString

    private var _pillCycleSpecific = MutableLiveData<String>()
    val pillCycleSpecific: LiveData<String> = _pillCycleSpecific

    private var _pillTimeList: MutableList<String> = mutableListOf("8:00", "13:00", "19:00")
    val pillTimeList: MutableList<String>
        get() = _pillTimeList

    private val _pillCount = MutableLiveData<Int>()
    var isMyPill: Boolean = false
    var canAddPill: Boolean = false
    var pillCount: Int = 0
    val _pillAddNavigateData = MutableLiveData<PillAddNavigateData>()
    val pillAddNavigateData: MutableLiveData<PillAddNavigateData> = _pillAddNavigateData

    private val _isComplete = MutableLiveData<Boolean>()
    val isComplete: LiveData<Boolean> get() = _isComplete
    private val _possibleCount = MutableLiveData<Int>()
    val possibleCount: LiveData<Int> get() = _possibleCount

    private val _pillListCount = MutableLiveData<Int>()
    val pillListCount: LiveData<Int> get() = _pillListCount

    fun setTimeList(timeList: MutableList<String>) {
        _timeList.value = timeList
    }

    fun addPillCycleSpecificDay(day: String) {
        _pillCycleSpecificDaysList.value?.add(day)
    }

    fun getCycle(): Int? {
        return _pillCycle.value
    }

    fun setCycle(takeInterval: Int) {
        Log.d("set Cycle", "$takeInterval")
        _pillCycle.value = takeInterval
    }

    fun setSpecificDay(days: String) {

    }

    fun deleteTime(time: String) {
        pillTimeList.remove(time)
    }

    fun addTime(time: String) {
        pillTimeList.add(time)
    }

    fun setPillList(value: MutableList<PillInfo>) {
        _pillList = value
        Log.d("set 됐습니다.", "$_pillList")
    }

    fun setPillTimeList(value: MutableList<String>) {
        _pillTimeList = value
    }

    fun setPillPeriod(period: MutableLiveData<String>) {
        _pillCycleSpecific = period
    }

    fun getPillPeriod(): String {
        return _pillCycleSpecific.value.toString()
    }

    fun setPillDays(days: String) {
        _pillCycleSpecificDaysString.value = days
    }

    fun getPillDays(): String {
        return _pillCycleSpecificDaysString.value.toString()
    }

    fun setIsComplete(complete: Boolean) {
        _isComplete.value = complete
    }

    fun setPillListCount(count: Int) {
        _pillListCount.value = count
    }

    fun setPossibleCount(count: Int) {
        _possibleCount.value = count
    }

    fun getPillAddNavigetData(): PillAddNavigateData? {
        return _pillAddNavigateData.value
    }

    fun setPillAddNavigetData(navigateData: PillAddNavigateData) {
        _pillAddNavigateData.value = navigateData
    }
}
