package com.example.sobok_android.presentation.view.share.request

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentShareRequestSaveBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.share.request.viewmodel.ShareRequestViewModel
import com.example.sobok_android.util.closeKeyboard
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.regex.Pattern

class ShareRequestSaveFragment :
    BindingFragment<FragmentShareRequestSaveBinding>(R.layout.fragment_share_request_save) {
    private val shareRequestViewModel: ShareRequestViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickEvent()
        initNickName()
        setEtShareRequestSaveTextChangedListener()
        setEtSearchSetOnKeyListener()
        setEtShareRequestOnFocusChangeListener()
    }

    private fun initNickName() {
        val nickname: String = arguments?.getString("memberName").toString()
        binding.nickName = nickname
        binding.textSize = 0
    }

    private fun initClickEvent() {
        with(binding) {
            ivShareRequestSaveDelete.setOnClickListener {
                etShareRequestSave.text = null
                etShareRequestSave.requestFocus()
            }

            binding.clShareRequestSave.setOnClickListener {
                binding.etShareRequestSave.clearFocus()
                requireContext().closeKeyboard(binding.etShareRequestSave)
            }

            btnRequest.setOnClickListener {
                //TODO: 이미 공유된 아이디인 경우 뷰가 없음 [질문]
                if (it.isSelected) {
                    shareRequestViewModel.postSearchResult(
                        arguments?.getInt("memberId") ?: 0,
                        arguments?.getString("memberName") ?: "테스트"
                    )
                    shareRequestViewModel.setIsShareRequestClick(true)
                    requireActivity().finish()
                }
            }
        }
    }

    private fun setEtSearchSetOnKeyListener() {
        binding.etShareRequestSave.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                requireContext().closeKeyboard(binding.etShareRequestSave)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun setEtShareRequestSaveTextChangedListener() {
        with(binding) {
            val ps =
                Pattern.compile("^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$")

            etShareRequestSave.addTextChangedListener {
                isEditable = !it.isNullOrEmpty()
                if (it != null) {
                    textSize = it.length
                    if (it.isNotEmpty()) {
                        isError = false
                        btnRequest.isSelected = true
                        if (it.length < 2) {
                            isError = true
                            btnRequest.isSelected = false
                            tvShareRequestSaveError.text =
                                getString(R.string.share_request_save_error_more)
                        } else if (!ps.matcher(it).matches()) {
                            isError = true
                            btnRequest.isSelected = false
                            tvShareRequestSaveError.text =
                                getString(R.string.share_request_save_error_special)
                        } else if (etShareRequestSave.text.length > 10) {
                            etShareRequestSave.setText(it.toString().subSequence(0, 10))
                            etShareRequestSave.setSelection(10)
                        }
                    } else {
                        btnRequest.isSelected = false
                        isError = false
                    }
                }
            }
        }
    }

    private fun setEtShareRequestOnFocusChangeListener() {
        with(binding) {
            etShareRequestSave.setOnFocusChangeListener { view, hasFocus ->
                clShareRequestSaveBar.isSelected = hasFocus
                isEditable = hasFocus
            }
        }
    }
}
