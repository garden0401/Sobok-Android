package com.example.sobok_android.presentation.view.myinfo

import android.os.Bundle
import android.view.KeyEvent
import androidx.core.widget.addTextChangedListener
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityMyNicknameEditBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.util.CustomSnackBar
import com.example.sobok_android.util.closeKeyboard
import com.example.sobok_android.util.showKeyboard
import java.util.regex.Pattern


class MyNicknameEditActivity :
    BindingActivity<ActivityMyNicknameEditBinding>(R.layout.activity_my_nickname_edit) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initClickEvent()
        initTextChangeEvent()
        initData()
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
                //TODO: Confirm 로직
            }
            tvDoubleCheck.setOnClickListener {
                if (tvDoubleCheck.isEnabled) {
                    //TODO: 닉네임 서버로 보내고 중복값 받아서 true false snackbar 띄우기
                    CustomSnackBar.make(
                        binding.colMyNicknameEdit,
                        getString(R.string.usable_nickname_message)
                    )
                        .show()
                    //TODO: snackbar 키보드 위로 올리기
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