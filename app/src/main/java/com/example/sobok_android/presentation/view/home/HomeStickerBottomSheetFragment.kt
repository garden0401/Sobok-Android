package com.example.sobok_android.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentHomeBinding
import com.example.sobok_android.databinding.FragmentHomeStickerBottomSheetBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.bind

class HomeStickerBottomSheetFragment : BottomSheetDialogFragment()  {
    lateinit var _binding: FragmentHomeStickerBottomSheetBinding
    private val binding get() = _binding!!

    private var stickerBottomSheetCloseClickListener : ((Boolean) -> Unit)? = null

    fun setStickerBottomSheetCloseClickListener(listener: (Boolean) -> Unit) {
        stickerBottomSheetCloseClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeStickerBottomSheetBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivHomeBottomSheetClose.setOnClickListener {
            dismiss()
            stickerBottomSheetCloseClickListener?.invoke(false)
        }
    }



}

