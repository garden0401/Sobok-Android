package com.example.sobok_android.presentation.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.model.share.request.GroupData
import com.example.sobok_android.domain.model.share.request.ShareRequestSuccessData
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import com.example.sobok_android.domain.repository.share.request.ShareRequestRepository
import com.example.sobok_android.presentation.view.pill.add.PillAddNavigateData
import kotlinx.coroutines.launch

class MainViewModel(
    private val shareRequestRepository: ShareRequestRepository,
    private val pillAddRepository: PillAddRepository
) : ViewModel() {
    private var _isHome = MutableLiveData<Boolean>(true)
    val isHome: LiveData<Boolean> = _isHome
    fun setIsHome(value: Boolean) {
        _isHome.value = value
    }

    private val _groupData = MutableLiveData<List<GroupData.MemberInfo>>()
    var groupData: LiveData<List<GroupData.MemberInfo>> = _groupData

    private var _memberInfoList = MutableLiveData<List<ShareRequestSuccessData.MemberInfo>>()
    val memberInfoList: LiveData<List<ShareRequestSuccessData.MemberInfo>> = _memberInfoList

    private val _selectedMemberName = MutableLiveData<String>()
    var selectedMemberName: LiveData<String> = _selectedMemberName
    fun setSelectedMemberName(value: String) {
        _selectedMemberName.value = value
    }

    // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
    private val _isStickerClick = MutableLiveData<Boolean>(false)
    private val _pillCount = MutableLiveData<Int>()
    var _pillAddNavigateData = MutableLiveData<PillAddNavigateData>()
    val pillAddNavigateData: LiveData<PillAddNavigateData> get() = _pillAddNavigateData
    val pillCount: LiveData<Int> get() = _pillCount
    var isStickerClick: LiveData<Boolean> = _isStickerClick

    fun setIsStickerClick(value: Boolean) {
        _isStickerClick.value = value
    }

    fun getGroupData() = viewModelScope.launch {
        runCatching { shareRequestRepository.getGroupData() }
            .onSuccess {
                _groupData.postValue(it.data)
                Log.d("groupData-server", "success${it}")
            }
            .onFailure {
                it.printStackTrace()
                Log.d("groupData-server", "fail${it.message}")
            }
    }

    fun setNavigateData(navigateData: PillAddNavigateData) {
        Log.d("MainViewModel setNavigateDate", "$navigateData")
        _pillAddNavigateData.value = navigateData
        Log.d("MainViewModel setNavigateDate2222222222222", "${_pillAddNavigateData.value}")
    }

    fun getPillCount() = viewModelScope.launch {
        runCatching { pillAddRepository.getPillCount() }
            .onSuccess {
                _pillCount.postValue(it.pillCount) // 서버랑 통신을 잘 했나 안했나 확인용
                // _pillCount를 받은 pillCount를  Acticity 나 Fragment에서 observe한다.
                Log.d("server-pill-add-pill-count-success", "약 개수 불러오기 성공")
            }
            .onFailure {
                it.printStackTrace()
                Log.d("server-pill-add-pill-count-fail", "약 개수 불러오기 실패")
            }
    }

}