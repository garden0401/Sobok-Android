package com.example.sobok_android.presentation.view.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
    private val _isEditClick = MutableLiveData<Boolean>(false)
    var isEditClick : LiveData<Boolean> = _isEditClick

    fun setIsEditClick(value: Boolean) {
        _isEditClick.value = value
    }
}