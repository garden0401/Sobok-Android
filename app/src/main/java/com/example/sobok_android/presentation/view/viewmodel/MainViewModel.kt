package com.example.sobok_android.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _isStickerClick = MutableLiveData<Boolean>(false)
    var isStickerClick : LiveData<Boolean> = _isStickerClick

    fun setIsStickerClick(value: Boolean) {
        _isStickerClick.value = value
    }


}