package com.example.sobok_android.presentation.view.pill.add

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sobok_android.databinding.FragmentPillAddBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PillAddBottomSheetFragment : BottomSheetDialogFragment() {
    lateinit var _binding: FragmentPillAddBottomSheetBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPillAddBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(requireContext(), PillAddActivity::class.java)

        binding.clPillAddMyPill.setOnClickListener {
            startActivity(intent)
        }
        binding.clPillAddSendPill.setOnClickListener {
            startActivity(intent)
        }
        binding.ivCancel.setOnClickListener {
            dismiss()
        }
    }
}