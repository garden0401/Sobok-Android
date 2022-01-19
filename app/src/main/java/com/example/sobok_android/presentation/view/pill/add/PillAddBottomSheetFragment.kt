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
    var isMyPill: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPillAddBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.clPillAddMyPill.setOnClickListener {
            navigateToMyPillAdd()
        }
        binding.clPillAddSendPill.setOnClickListener {
            navigateToSendPillAdd()
        }
        binding.ivCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun navigateToMyPillAdd() {
        val intent = Intent(requireContext(), PillAddActivity::class.java)
        intent.putExtra("isMyPill", true)
        startActivity(intent)
    }

    private fun navigateToSendPillAdd() {
        val intent = Intent(requireContext(), PillAddActivity::class.java)
        intent.putExtra("isMyPill", false)
        startActivity(intent)
    }

    fun getMyPIll() : Boolean{
        return isMyPill
    }
}