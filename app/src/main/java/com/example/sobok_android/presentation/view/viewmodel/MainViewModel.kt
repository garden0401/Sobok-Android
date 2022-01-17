package com.example.sobok_android.presentation.view.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기
    private val _isStickerClick = MutableLiveData<Boolean>(false)
    var isStickerClick : LiveData<Boolean> = _isStickerClick

    fun setIsStickerClick(value: Boolean) {
        _isStickerClick.value = value
    }

    // 홈(메인) 약 리스트 수정시 context 버튼 클릭-popup menu 띄우기 (고차함수 써보기)
    private val _isEditContextClick = MutableLiveData<Boolean>(false)
    var isEditContextClick : LiveData<Boolean> = _isEditContextClick

    fun setIsEditContextClick(value: Boolean) {
        _isEditContextClick.value = value
    }



}