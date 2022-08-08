package com.example.sobok_android.presentation.view.myinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentSettingTermsOfUseBinding
import com.example.sobok_android.presentation.base.BindingFragment

class SettingTermsOfUseFragment :
    BindingFragment<FragmentSettingTermsOfUseBinding>(R.layout.fragment_setting_terms_of_use) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickEvent()
    }

    private fun initClickEvent() {
        with(binding) {
            //TODO: 주소 변경 예정 -> 변수로 관리
            tvPrivacyPolicy.setOnClickListener { navigateToWebSite("https://www.notion.so/baejiann120/07dac60488784381bfca52ffb04adbec") }
            ibMorePrivacyPolicy.setOnClickListener { navigateToWebSite("https://www.notion.so/baejiann120/07dac60488784381bfca52ffb04adbec") }

            tvSobokTermsOfService.setOnClickListener { navigateToWebSite("https://www.notion.so/baejiann120/07dac60488784381bfca52ffb04adbec") }
            ibMoreSobokTermsOfService.setOnClickListener { navigateToWebSite("https://www.notion.so/baejiann120/07dac60488784381bfca52ffb04adbec") }
        }
    }

    //TODO: 공통함수
    private fun navigateToWebSite(address: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(address))
        startActivity(intent)
    }
}