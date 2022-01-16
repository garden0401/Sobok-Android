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
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.bind

class HomeStickerBottomSheetFragment : BottomSheetDialogFragment()  {
    lateinit var _binding: FragmentHomeStickerBottomSheetBinding
    private val binding get() = _binding!!

    private lateinit var homeStickerBottomSheetAdapter: HomeStickerBottomSheetAdapter

    lateinit var behavior: BottomSheetBehavior<View>

    private var stickerBottomSheetCloseClickListener : ((Boolean) -> Unit)? = null

    override fun onStart() {
        super.onStart()
        behavior = BottomSheetBehavior.from(binding.clAa)
        behavior.isFitToContents = false
        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
    }

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

        initAdapter()

        binding.ivHomeBottomSheetClose.setOnClickListener {
            dismiss()
            stickerBottomSheetCloseClickListener?.invoke(false)
        }
    }

    private fun initAdapter() {
        homeStickerBottomSheetAdapter = HomeStickerBottomSheetAdapter()
        binding.rvHomeStickerBottomSheetContent.adapter = homeStickerBottomSheetAdapter

        homeStickerBottomSheetAdapter.homeStickerBottomSheet =
            listOf(
                HomeStickerBottomSheetData("", "정원"),
                HomeStickerBottomSheetData("", "수현"),
                HomeStickerBottomSheetData("", "채영"),
                HomeStickerBottomSheetData("", "지민"),
                HomeStickerBottomSheetData("", "효영"),
                HomeStickerBottomSheetData("", "고현"),
                HomeStickerBottomSheetData("", "현지"),
                HomeStickerBottomSheetData("", "지안"),
                HomeStickerBottomSheetData("", "원정"),
                HomeStickerBottomSheetData("", "개미"),
                HomeStickerBottomSheetData("", "정원"),
                HomeStickerBottomSheetData("", "수현"),
                HomeStickerBottomSheetData("", "채영"),
                HomeStickerBottomSheetData("", "지민"),
                HomeStickerBottomSheetData("", "효영"),
                HomeStickerBottomSheetData("", "고현"),
                HomeStickerBottomSheetData("", "현지"),
                HomeStickerBottomSheetData("", "지안"),
                HomeStickerBottomSheetData("", "원정"),
                HomeStickerBottomSheetData("", "개미"),
                HomeStickerBottomSheetData("", "정원"),
                HomeStickerBottomSheetData("", "수현"),
                HomeStickerBottomSheetData("", "채영"),
                HomeStickerBottomSheetData("", "지민"),
                HomeStickerBottomSheetData("", "효영"),
                HomeStickerBottomSheetData("", "고현"),
                HomeStickerBottomSheetData("", "현지"),
                HomeStickerBottomSheetData("", "지안"),
                HomeStickerBottomSheetData("", "원정"),
                HomeStickerBottomSheetData("", "개미")
           )
    }



}

