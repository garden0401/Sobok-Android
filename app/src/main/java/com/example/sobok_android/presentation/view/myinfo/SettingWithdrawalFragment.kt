package com.example.sobok_android.presentation.view.myinfo

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentSettingWithdrawalBinding
import com.example.sobok_android.presentation.base.BindingFragment

class SettingWithdrawalFragment :
    BindingFragment<FragmentSettingWithdrawalBinding>(R.layout.fragment_setting_withdrawal) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setTextChangeEvent()
        initClickEvent()
    }

    private fun initData() {
        binding.reasonLength = 0
    }

    private fun setTextChangeEvent() {
        with(binding) {
            etWithdrawalReason.addTextChangedListener {
                if (it != null) {
                    reasonLength = it.length
                    if (it.length > 2000) {
                        etWithdrawalReason.setText(it.toString().subSequence(0, 2000))
                        etWithdrawalReason.setSelection(2000)
                    }
                }
            }
        }
    }

    private fun initClickEvent() {
        with(binding) {
            btnWithdrawal.setOnClickListener {

            }
        }
    }
}