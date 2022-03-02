package com.example.sobok_android.presentation.view.user.request.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.data.model.request.login.ReqSignUpSuccessData
import com.example.sobok_android.domain.model.login.request.SignUpSuccessData
import com.example.sobok_android.domain.repository.login.request.SignUpRepository
import kotlinx.coroutines.launch

class SignUpViewModel(private val signUpRepository: SignUpRepository) :
    ViewModel() {

    private var _memberInfo = MutableLiveData<SignUpSuccessData.MemberInfo>()
    val memberInfo : LiveData<SignUpSuccessData.MemberInfo> = _memberInfo

    private var _email: String = ""
    var email: String = _email
        set(value) {
            _email = value
            field = value
        }

    private var _name: String = ""
    var name: String = _name
        set(value) {
            _name = value
            field = value
        }


    private var _password: String = ""
    var password: String = _password
        set(value) {
            _password = value
            field = value
        }

    fun postSignUp() = viewModelScope.launch() {
        runCatching { signUpRepository.postSignUpResult(ReqSignUpSuccessData(_email, _name, _password)) }
            .onSuccess {
                _memberInfo.postValue(it.data)
                Log.d("searchResultSuccess-server", "success${it}")
            }
            .onFailure {
                it.printStackTrace()
            }
    }
}