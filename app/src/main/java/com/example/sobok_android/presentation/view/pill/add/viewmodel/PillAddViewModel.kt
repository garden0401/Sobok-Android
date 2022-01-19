package com.example.sobok_android.presentation.view.pill.add.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.model.pill.pilladd.PillListData
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import kotlinx.coroutines.launch

class PillAddViewModel(val pillAddRepository: PillAddRepository) : ViewModel() {

    private val _pillList = MutableLiveData<PillListData>()
    private val _pillTime = MutableLiveData<List<String>>()
    val pillList: LiveData<PillListData> get() = _pillList
    private val _isComplete = MutableLiveData<Boolean>()
    val isComplete: LiveData<Boolean> get() = _isComplete

    fun setIsComplete(complete: Boolean) {
        _isComplete.value = complete
        Log.d("완료버튼", "완료버튼")

        // 매개변수로 받은 bool 값을 내부 변수에 넣어준다.
    }

    fun getPillList() = viewModelScope.launch {
        runCatching { pillAddRepository.getPillList() }
            .onSuccess {
                _pillList.postValue(it) // _pillList = it
            }
            .onFailure {
                Log.d("server-pill-add-read-fail", "실패할일이 없다")
            }
    }
}