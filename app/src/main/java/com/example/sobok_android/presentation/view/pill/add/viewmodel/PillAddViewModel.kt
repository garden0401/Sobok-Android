package com.example.sobok_android.presentation.view.pill.add.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.model.pill.pilladd.PillCountData
import com.example.sobok_android.domain.model.pill.pilladd.PillListData
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import com.example.sobok_android.presentation.view.pill.add.PillAddNavigateData
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class PillAddViewModel(val pillAddRepository: PillAddRepository) : ViewModel() {

    // repository함수를 불러오면.. data 불러오는거
    private val _pillList = MutableLiveData<MutableList<PillListData.PillInfo>>()
    private val _pillTime = MutableLiveData<Int>()
    private val _pillCount = MutableLiveData<Int>()
    var isMyPill:Boolean = false
    var canAddPill:Boolean = false
    var pillCount: Int = 0
    val _pillAddNavigateData = MutableLiveData<PillAddNavigateData>()
    val pillAddNavigateData: MutableLiveData<PillAddNavigateData> = _pillAddNavigateData
    var pillList: LiveData<MutableList<PillListData.PillInfo>> = _pillList
    val pillTime: LiveData<Int> get() = _pillTime
    private val _isComplete = MutableLiveData<Boolean>()
    val isComplete: LiveData<Boolean> get() = _isComplete

    fun init() {
        val mainViewModel: MainViewModel
        // canAddPill =
    }
    fun setPillList(value: MutableList<PillListData.PillInfo>) {
        _pillList.value = value

    }

    fun setIsComplete(complete: Boolean) {
        _isComplete.value = complete
    }

    fun getPillAddNavigetData(navigateData: PillAddNavigateData): PillAddNavigateData {

        return navigateData
    }

    fun setPillAddNavigetData(navigateData: PillAddNavigateData) {
        _pillAddNavigateData.value = navigateData
    }
/*
    fun getPillList() = viewModelScope.launch {
        runCatching { pillAddRepository.getPillList() }
            .onSuccess {
                _pillList.postValue(it) // _pillList = it
            }
            .onFailure {
                Log.d("server-pill-add-read-fail", "실패할일이 없다")
            }
    }
    */

}