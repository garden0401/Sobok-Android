package com.example.sobok_android.util

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.sobok_android.R
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_FADE
import com.google.android.material.snackbar.Snackbar

class CustomSnackBar(view: View, message: String) {

    companion object {
        fun make(view: View, message: String) = CustomSnackBar(view, message)
    }

    private val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
    private val snackBarView = snackBar.view
    private val snackBarLayout = snackBarView.layoutParams as CoordinatorLayout.LayoutParams

    init {
        initView()
    }

    private fun initView() {
        snackBarLayout.apply {
            gravity = Gravity.BOTTOM or Gravity.CENTER
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        val snackBarTextView = snackBarView.findViewById<TextView>(R.id.snackbar_text)
        snackBarTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackBar.animationMode = ANIMATION_MODE_FADE
    }

    fun show() {
        snackBar.show()
    }
}