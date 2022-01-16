package com.example.sobok_android.presentation.view.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ItemHomePillListDetailBinding


class HomePillListDetailAdapter :
    RecyclerView.Adapter<HomePillListDetailAdapter.HomePillListDetailViewHolder>() {

    private val _homePillListDetail = mutableListOf<HomePillListData.PillDetailData>()

    var homePillListDetail: List<HomePillListData.PillDetailData> = _homePillListDetail
        set(value) {
            _homePillListDetail.clear()
            _homePillListDetail.addAll(value)
            notifyDataSetChanged()
        }

    private var stickerClickListener: ((Boolean) -> Unit)? = null

    fun setStickerClickListener(listener: (Boolean) -> Unit) {
        stickerClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePillListDetailViewHolder {
        val binding = ItemHomePillListDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return HomePillListDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomePillListDetailViewHolder, position: Int) {
        holder.onBind(_homePillListDetail[position])
    }

    override fun getItemCount(): Int = _homePillListDetail.size

    inner class HomePillListDetailViewHolder(private val binding: ItemHomePillListDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HomePillListData.PillDetailData) {
            binding.tvHomePillListPillName.text = data.pillName
            binding.ivHomePillListCheck.isSelected = data.isCheck
            binding.ivHomePillListStickerOne.setImageResource(R.drawable.rectangle_pill_list_sticker_example)
            binding.ivHomePillListColor.setImageResource(R.drawable.oval_pill_list_color)
            binding.tvHomePillListStickerCount.text = data.stickerCount

            // 약 리스트 체크 & 체크 취소
            binding.ivHomePillListCheck.setOnClickListener {
                binding.ivHomePillListCheck.isSelected = !binding.ivHomePillListCheck.isSelected
            }


            binding.ivHomePillListStickerOne.setOnClickListener {
                stickerClickListener?.invoke(true)
                Log.d("detailAdapter", "true")
            }

            binding.tvHomePillListPillName.setOnClickListener {
                stickerClickListener?.invoke(true)
                Log.d("tvtv", "click")
            }

            // 스피너 어댑터 설정
            // binding.spHomePillList.adapter = ArrayAdapter.createFromResource(this, R.array.spinnerList, android.R.layout.simple_spinner_item)


        }
    }
}
