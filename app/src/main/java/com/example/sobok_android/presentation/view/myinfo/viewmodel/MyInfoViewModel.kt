package com.example.sobok_android.presentation.view.myinfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.model.myinfo.MyPillData
import com.example.sobok_android.domain.repository.myinfo.MyInfoRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MyInfoViewModel(
    private val myInfoRepository: MyInfoRepository
) : ViewModel() {

    private val _myPillList = MutableLiveData<List<MyPillData>>()
    var myPillList: LiveData<List<MyPillData>> = _myPillList

    fun getMyPillList() = viewModelScope.launch {
        runCatching { myInfoRepository.getMyPillList() }
            .onSuccess {
                _myPillList.value = it
                Timber.tag("MyInfoPillList-server").d(it.toString())
            }
            .onFailure {
                it.printStackTrace()
                Timber.tag("MyInfoPillList-server").d("fail")
            }
    }
}