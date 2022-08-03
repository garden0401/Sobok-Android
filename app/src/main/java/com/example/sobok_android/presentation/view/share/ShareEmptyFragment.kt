package com.example.sobok_android.presentation.view.share

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentShareEmptyBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.share.request.ShareRequestActivity

class ShareEmptyFragment :
    BindingFragment<FragmentShareEmptyBinding>(R.layout.fragment_share_empty) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBtnShareRequestClickListener()
    }

    private fun setBtnShareRequestClickListener() {
        binding.btnShareRequest.setOnClickListener {
            shareRequestActivityLauncher.launch(
                Intent(requireContext(), ShareRequestActivity::class.java)
            )
        }
    }

    private val shareRequestActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            if (it.data!!.getBooleanExtra("isShareRequest", false)) {
                val builder = AlertDialog.Builder(requireContext()).apply {
                    setTitle(getString(R.string.share_request_calendar_message))
                    setMessage(getString(R.string.share_request_calendar_message_detail))
                    setPositiveButton(getString(R.string.confirm)) { _, _ ->
                    }
                }
                builder.create().show()
            }
        }
    }

}