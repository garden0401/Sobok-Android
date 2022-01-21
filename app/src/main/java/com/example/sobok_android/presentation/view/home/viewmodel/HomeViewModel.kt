package com.example.sobok_android.presentation.view.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.model.home.HomePillListData
import com.example.sobok_android.domain.model.home.PillListData
import com.example.sobok_android.domain.repository.calendar.CalendarRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val calendarRepository: CalendarRepository): ViewModel() {

    // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
    private val _isEditClick = MutableLiveData<Boolean>(false)
    var isEditClick : LiveData<Boolean> = _isEditClick

    fun setIsEditClick(value: Boolean) {
        _isEditClick.value = value
    }

    private val _selectedDate = MutableLiveData<String>("2022-01-14")
    var selectedDate : LiveData<String> = _selectedDate

    fun setSelectedDate(value: String) {
        _selectedDate.value = value
    }

    private val _homePillList = MutableLiveData<PillListData>()
    var homePillList : LiveData<PillListData> = _homePillList


    fun getHomePillListData() = viewModelScope.launch {
        runCatching { calendarRepository.getHomePillListData(requireNotNull(_selectedDate.value)) }
            .onSuccess {
                _homePillList.postValue(it)
                Log.d("homepillList-server", "${it.data}")
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    private val _memberId = MutableLiveData<Int>()
    var memberId : LiveData<Int> = _memberId
    fun postMemberId(value: Int) {
        _memberId.value = value
    }

    private val _sharePillList = MutableLiveData<PillListData>()
    var sharePillList : LiveData<PillListData> = _sharePillList


    fun getSharePillListData() = viewModelScope.launch { //_memberId.value
        runCatching { calendarRepository.getSharePillList(requireNotNull(24), requireNotNull(_selectedDate.value)) }
            .onSuccess {
                _sharePillList.postValue(it)
                Log.d("공유-server", "${it.data}")
            }
            .onFailure {
                it.printStackTrace()
                Log.d("공유-server", "${it}")
            }
    }
}