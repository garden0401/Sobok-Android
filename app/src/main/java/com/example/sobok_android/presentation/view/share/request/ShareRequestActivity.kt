package com.example.sobok_android.presentation.view.share.request

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityShareRequestBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.share.request.viewmodel.ShareRequestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShareRequestActivity : BindingActivity<ActivityShareRequestBinding>(R.layout.activity_share_request) {
    private lateinit var navController: NavController
    private val shareRequestViewModel: ShareRequestViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeIsShareRequestClick()
        initTopBar()
        initClickEvent()
    }

    private fun observeIsShareRequestClick() {
        shareRequestViewModel.isShareRequestClick.observe(this) {
            intent.putExtra("isShareRequest", true)
            setResult(RESULT_OK, intent)
        }
    }

    private fun initTopBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_share_request) as NavHostFragment
        navController = navHostFragment.navController
        with(binding) {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                isShareRequestSearch = when (destination.id) {
                    R.id.shareRequestSearchFragment -> {
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }

    private fun initClickEvent() {
        with(binding) {
            ivShareRequestBack.setOnClickListener {
                navController.popBackStack()
            }
            ivShareRequestClose.setOnClickListener {
                intent.putExtra("isShareRequest", false)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        intent.putExtra("isShareRequest", false)
        super.onBackPressed()
    }
}