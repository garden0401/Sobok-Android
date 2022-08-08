package com.example.sobok_android.util

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.sobok_android.R
import com.example.sobok_android.databinding.GenericDialogBinding

class CustomDialog(val context: Context) {
    private val dialog = Dialog(context)

    // 기본 다이얼로그
    fun genericDialog(
        dialogText: DialogData,
        cancel: () -> Unit,
        complete: () -> Unit
    ) {
        val binding = DataBindingUtil.inflate<GenericDialogBinding>(
            LayoutInflater.from(context),
            R.layout.generic_dialog,
            null,
            false
        )
        binding.dialogText = dialogText
        binding.tvNotAccept.setOnClickListener {
            cancel()
            dialog.dismiss()
        }
        binding.tvAccept.setOnClickListener {
            complete()
            dialog.dismiss()
        }

        dialog.setContentView(binding.root)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog.show()

    }

    data class DialogData(
        val title: String?,
        val cancel: String,
        val complete: String
    )
}