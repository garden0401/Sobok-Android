package com.example.sobok_android.presentation.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import com.example.sobok_android.presentation.view.pill.add.PillAddNavigateData
import kotlinx.coroutines.launch

class MainViewModel(val pillAddRepository: PillAddRepository)  : ViewModel() {

    // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
    private val _isStickerClick = MutableLiveData<Boolean>(false)
    private val _pillCount = MutableLiveData<Int>()
    var _isMyPill = MutableLiveData<Boolean>()
    var _canAddPill = MutableLiveData<Boolean>()
    val isMyPill: MutableLiveData<Boolean> get() = _isMyPill
    val canAddPill: MutableLiveData<Boolean> get() = _canAddPill
    var _pillAddNavigateData = MutableLiveData<PillAddNavigateData>()
    val pillAddNavigateData: LiveData<PillAddNavigateData> get() = _pillAddNavigateData
    val pillCount: LiveData<Int> get() = _pillCount
    var isStickerClick : LiveData<Boolean> = _isStickerClick

    lateinit var sendPillCount: Int


    fun setIsStickerClick(value: Boolean) {
        _isStickerClick.value = value
    }

    fun setIsMyPill(getisMyPill: Boolean) {
        _isMyPill.value = getisMyPill
    }

    fun setCanAddPill(canAddPill: Boolean) {
        _canAddPill.value = canAddPill
    }

    fun setPillCount(pillCount: Int) {
        _pillCount.value = pillCount
    }

    fun setNavigateData(navigateData: PillAddNavigateData) {
        Log.d("MainViewModel setNavigateDate", "$navigateData")
        _pillAddNavigateData.value = navigateData
        Log.d("MainViewModel setNavigateDate2222222222222", "${_pillAddNavigateData.value}")
    }

    fun getPillCount() = viewModelScope.launch {
        runCatching { pillAddRepository.getPillCount() }
            .onSuccess {
                _pillCount.postValue(it.pillCount)
                Log.d("server-pill-add-pill-count-success", "약 개수 불러오기 성공")
            }
            .onFailure {
                it.printStackTrace()
                Log.d("server-pill-add-pill-count-fail", "약 개수 불러오기 실패")
            }
    }

}