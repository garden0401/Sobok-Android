package com.example.sobok_android.presentation.view.pill.add.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sobok_android.domain.model.pill.pilladd.PillListData
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import com.example.sobok_android.presentation.view.pill.add.PillAddNavigateData

class PillAddViewModel(val pillAddRepository: PillAddRepository) : ViewModel() {

    // repository함수를 불러오면.. data 불러오는거
    private var _pillList: MutableList<PillListData.PillInfo> = mutableListOf()
    val pillList: MutableList<PillListData.PillInfo>
        get() = _pillList

    private val _pillTime = MutableLiveData<Int>()
    val pillTime: LiveData<Int> = _pillTime

    private val _pillCycle = MutableLiveData<Int>()
    val pillCycle: LiveData<Int> = _pillCycle

    private var _pillCycleSpecificDaysList: MutableList<String> = mutableListOf()
    val pillCycleSpecificDaysList: MutableList<String>
        get() = _pillCycleSpecificDaysList

    private var _pillCycleSpecific = MutableLiveData<String>()
    val pillCycleSpecific: LiveData<String> = _pillCycleSpecific

    private var _pillTimeList: MutableList<String> = mutableListOf("8시", "1시", "7시")
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

    fun setCycle(takeInterval: Int) {
        Log.d("set Cycle", "$takeInterval")
        _pillCycle.value = takeInterval
    }

    fun setPillList(value: MutableList<PillListData.PillInfo>) {
        _pillList = value
        Log.d("set 됐습니다.", "$_pillList")
    }

    fun setPillPeriod(period: MutableLiveData<String>) {
        Log.d("value : ", "${period.value}")
        _pillCycleSpecific = period
        Log.d("뭡니까 ", "${_pillCycleSpecific.value}")
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

    fun getPillAddNavigetData(navigateData: PillAddNavigateData): PillAddNavigateData {
        return navigateData
    }

    fun setPillAddNavigetData(navigateData: PillAddNavigateData) {
        _pillAddNavigateData.value = navigateData
    }
}
