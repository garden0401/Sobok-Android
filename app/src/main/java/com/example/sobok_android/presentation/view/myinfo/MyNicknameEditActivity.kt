package com.example.sobok_android.presentation.view.myinfo

import android.os.Bundle
import android.view.KeyEvent
import androidx.core.widget.addTextChangedListener
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityMyNicknameEditBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.myinfo.viewmodel.MyInfoViewModel
import com.example.sobok_android.util.CustomSnackBar
import com.example.sobok_android.util.closeKeyboard
import com.example.sobok_android.util.showKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.regex.Pattern


class MyNicknameEditActivity :
    BindingActivity<ActivityMyNicknameEditBinding>(R.layout.activity_my_nickname_edit) {
    private val myInfoViewModel: MyInfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initClickEvent()
        initTextChangeEvent()
        initData()
        observeData()
    }

    private fun initData() {
        //TODO: 닉네임 처음 입력된 상태로 시작
        with(binding) {
            nickName = "닉네임"
            etSearch.setText("닉네임")
            etSearch.setSelection("닉네임".length)
            clSearchBar.isSelected = true
        }
    }

    private fun initClickEvent() {
        with(binding) {
            ibClose.setOnClickListener {
                finish()
            }
            ivDeleteText.setOnClickListener {
                with(etSearch) {
                    setText("")
                    requestFocus()
                }
            }
            clSearchBar.setOnClickListener {
                etSearch.requestFocus()
                showKeyboard(etSearch)
            }
            clMyNicknameEdit.setOnClickListener {
                etSearch.clearFocus()
                closeKeyboard(etSearch)
            }
            btnConfirm.setOnClickListener {
                if (myInfoViewModel.isDoubleCheckClick.value == false || etSearch.text.toString() != myInfoViewModel.doubleCheckNickname) {
                    //TODO: snackbar 키보드 위로 올리기
                    CustomSnackBar.make(
                        binding.colMyNicknameEdit,
                        getString(R.string.please_double_check)
                    ).show()
                } else if (myInfoViewModel.isNicknameAvailable.value == false) {
                    myInfoViewModel.setIsNicknameAvailable(false)
                } else {
                    //todo; 서버로 닉네임 보내기
                    //myInfoViewModel.putUserName(etSearch.text.toString())
                    finish()
                }
            }
            tvDoubleCheck.setOnClickListener {
                if (tvDoubleCheck.isEnabled) {
                    myInfoViewModel.postUserName(etSearch.text.toString())
                }
            }
        }
    }

    private fun observeData() {
        myInfoViewModel.isNicknameAvailable.observe(this) {
            when (it) {
                true -> {
                    //TODO: snackbar 키보드 위로 올리기
                    CustomSnackBar.make(
                        binding.colMyNicknameEdit,
                        getString(R.string.usable_nickname_message)
                    ).show()
                }
                else -> {
                    //TODO: snackbar 키보드 위로 올리기
                    CustomSnackBar.make(
                        binding.colMyNicknameEdit,
                        getString(R.string.nickname_already_in_use_message)
                    ).show()
                }
            }
        }
    }

    private fun initTextChangeEvent() {
        val ps =
            Pattern.compile("^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$")

        with(binding) {
            etSearch.setOnFocusChangeListener { _, hasFocus ->
                clSearchBar.isSelected = hasFocus
                isTextExist = hasFocus
                if (hasFocus) showKeyboard(etSearch)
            }

            etSearch.addTextChangedListener {
                isTextExist = !it.isNullOrEmpty()
                if (it != null) {
                    if (it.isNotEmpty()) {
                        isError = false
                        //TODO: 닉네임으로 수정
                        isDoubleCheckEnable = it.toString() != "닉네임"
                        if (!ps.matcher(it).matches() || it.length < 2) {
                            isError = true
                            isDoubleCheckEnable = false
                        } else if (etSearch.text.length > 10) {
                            etSearch.setText(it.toString().subSequence(0, 10))
                            etSearch.setSelection(10)
                        }
                    } else {
                        isError = false
                        isDoubleCheckEnable = false
                    }
                }
            }

            etSearch.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    v.clearFocus()
                    closeKeyboard(etSearch)
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
    }
}