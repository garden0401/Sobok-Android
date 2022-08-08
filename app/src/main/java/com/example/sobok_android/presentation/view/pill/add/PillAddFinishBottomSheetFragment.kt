package com.example.sobok_android.presentation.view.pill.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddBottomSheetBinding
import com.example.sobok_android.databinding.FragmentPillAddFinishBottomSheetBinding
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PillAddFinishBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentPillAddFinishBottomSheetBinding
    private val binding get() = _binding
    private val pillAddViewModel: PillAddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPillAddFinishBottomSheetBinding.inflate(layoutInflater, container, false)

        setText()

        return binding.root
    }

    private fun setText() {
        Log.d("pillAddViewModel.pillCycle.toString()", "${pillAddViewModel.pillCycle.toString()}")
        binding.tvCycleDetail.text = pillAddViewModel.pillCycle.toString()
    }


}