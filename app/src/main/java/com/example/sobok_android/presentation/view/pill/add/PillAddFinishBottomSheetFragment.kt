package com.example.sobok_android.presentation.view.pill.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddBottomSheetBinding
import com.example.sobok_android.databinding.FragmentPillAddFinishBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PillAddFinishBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var _binding: FragmentPillAddFinishBottomSheetBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPillAddFinishBottomSheetBinding.inflate(layoutInflater, container, false)

        return binding.root
    }



}