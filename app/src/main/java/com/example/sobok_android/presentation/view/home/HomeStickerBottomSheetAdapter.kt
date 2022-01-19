package com.example.sobok_android.presentation.view.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentHomeStickerBottomSheetBinding
import com.example.sobok_android.databinding.ItemHomeStickerBottomSheetBinding

class HomeStickerBottomSheetAdapter : RecyclerView.Adapter<HomeStickerBottomSheetAdapter.HomeStickerBottomSheetViewHolder>() {

    private val _homeStickerBottomSheet = mutableListOf<HomeStickerBottomSheetData>()

    var homeStickerBottomSheet: List<HomeStickerBottomSheetData> = _homeStickerBottomSheet
        set(value) {
            Log.d("hahaha/bottomsheetitem", "${value}")
            _homeStickerBottomSheet.clear()
            _homeStickerBottomSheet.addAll(value)
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeStickerBottomSheetViewHolder {
        val binding = ItemHomeStickerBottomSheetBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return HomeStickerBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeStickerBottomSheetViewHolder, position: Int) {
        holder.onBind(_homeStickerBottomSheet[position])
    }

    override fun getItemCount(): Int = _homeStickerBottomSheet.size


    class HomeStickerBottomSheetViewHolder(private val binding: ItemHomeStickerBottomSheetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HomeStickerBottomSheetData) {
            binding.tvStickerSenderName.text = data.userName
            Log.d("rrrr", "onBind")
        }

    }
}









