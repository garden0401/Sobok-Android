package com.example.sobok_android.presentation.view.pill.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityPillAddBinding

class PillAddBottomSheetFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pill_add_bottom_sheet, container, false)
    }

    private fun gotoPillAddInfo() {
        val pillAddInfo = PillAddInfo()


    }

}