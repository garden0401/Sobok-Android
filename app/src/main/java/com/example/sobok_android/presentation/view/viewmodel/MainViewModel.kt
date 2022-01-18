package com.example.sobok_android.presentation.view.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
    private val _isStickerClick = MutableLiveData<Boolean>(false)
    var isStickerClick : LiveData<Boolean> = _isStickerClick

    fun setIsStickerClick(value: Boolean) {
        _isStickerClick.value = value
    }


}