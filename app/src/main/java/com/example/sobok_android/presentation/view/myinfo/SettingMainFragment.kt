package com.example.sobok_android.presentation.view.myinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentSettingMainBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.util.CustomDialog

class SettingMainFragment :
    BindingFragment<FragmentSettingMainBinding>(R.layout.fragment_setting_main) {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController()
        initClickEvent()
    }

    private fun initNavController() {
        navController = findNavController()
    }

    private fun initClickEvent() {
        with(binding) {
            //TODO: 단순화 하는 법 고민
            tvContactEmail.setOnClickListener { sendEmail() }
            ibMoreEmail.setOnClickListener { sendEmail() }

            tvTermsOfUse.setOnClickListener { navController.navigate(R.id.action_settingMainFragment_to_settingTermsOfUseFragment) }
            ibMoreTermsOfUse.setOnClickListener { navController.navigate(R.id.action_settingMainFragment_to_settingTermsOfUseFragment) }

            tvOpenSourceLicense.setOnClickListener { navigateToWebSite("https://www.notion.so/baejiann120/07dac60488784381bfca52ffb04adbec") }
            ibMoreOpenSourceLicense.setOnClickListener { navigateToWebSite("https://www.notion.so/baejiann120/07dac60488784381bfca52ffb04adbec") }

            tvWithdrawal.setOnClickListener { navController.navigate(R.id.action_settingMainFragment_to_settingWithdrawalFragment) }

            tvLogout.setOnClickListener {
                CustomDialog(requireContext()).genericDialog(
                    CustomDialog.DialogData(
                        title = getString(R.string.logout_message),
                        cancel = getString(R.string.cancel),
                        complete = getString(R.string.logout)
                    ),
                    cancel = {},
                    complete = {
                        //TODO: logout 서버 연결 & snackbar
                    }
                )
            }
        }
    }

    private fun sendEmail() {
        val emailAddress = "soboksobok.official@gmail.com"

        val intent = Intent(Intent.ACTION_SENDTO)
            .apply {
                type = "text/plain"
                data = Uri.parse("mailto:") // 이메일 앱에서만 인텐트 처리되도록 설정
                putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(emailAddress))
            }

        startActivity(intent)
    }

    //TODO: 공통함수
    private fun navigateToWebSite(address: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(address))
        startActivity(intent)
    }

}