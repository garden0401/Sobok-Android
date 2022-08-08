package com.example.sobok_android.presentation.view.myinfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.data.model.request.myinfo.ReqMyNicknameData
import com.example.sobok_android.domain.model.myinfo.MyPillData
import com.example.sobok_android.domain.repository.myinfo.MyInfoRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MyInfoViewModel(
    private val myInfoRepository: MyInfoRepository
) : ViewModel() {

    private val _myPillList = MutableLiveData<List<MyPillData>>()
    var myPillList: LiveData<List<MyPillData>> = _myPillList

    private var _doubleCheckNickname: String = ""
    val doubleCheckNickname: String
        get() = _doubleCheckNickname

    private val _isNicknameAvailable = MutableLiveData<Boolean>()
    var isNicknameAvailable: LiveData<Boolean> = _isNicknameAvailable

    fun setIsNicknameAvailable(value: Boolean) {
        _isNicknameAvailable.value = value
    }

    private val _isDoubleCheckClick = MutableLiveData<Boolean>(false)
    var isDoubleCheckClick: LiveData<Boolean> = _isDoubleCheckClick

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

    fun postUserName(userName: String) = viewModelScope.launch {
        kotlin.runCatching { myInfoRepository.postUserName(ReqMyNicknameData(userName)) }
            .onSuccess {
                _doubleCheckNickname = userName
                _isDoubleCheckClick.value = true
                _isNicknameAvailable.value = it.success
                Timber.tag("DoubleCheckUserName-server").d(it.toString())
            }
            .onFailure {
                _doubleCheckNickname = userName
                _isDoubleCheckClick.value = false
                _isNicknameAvailable.value = false
                it.printStackTrace()
            }
    }

    fun putUserName(userName: String) = viewModelScope.launch {
        kotlin.runCatching { myInfoRepository.putUserName(ReqMyNicknameData(userName)) }
            .onSuccess { Timber.tag("EditUserName-server").d(it.toString()) }
            .onFailure { it.printStackTrace() }
    }
}