package com.example.sobok_android.presentation.view.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.PopupMenu
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

    // 홈(메인) 약 리스트 수정시 context 버튼 클릭-popup menu 띄우기 (고차함수 써보기)
    private var editContextClickListener: ((Boolean, View) -> Unit)? = null

    fun setEditContextClickListener(listener: (Boolean, View) -> Unit) {
        editContextClickListener = listener
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

            binding.ivHomePillListStickerOne.setOnClickListener {
                stickerClickListener?.invoke(true)
                Log.d("detailAdapter", "true")
            }

            binding.tvHomePillListPillName.setOnClickListener {
                stickerClickListener?.invoke(true)
                Log.d("tvtv", "click")
            }

//            // 홈(메인) 약 리스트 수정시 context 버튼 클릭-popup menu 띄우기 (고차함수 써보기)
//            binding.ivHomePillListEdit.setOnClickListener {
//                Log.d("popupDetail", "success${it}")
//                editContextClickListener?.invoke(true, it)
//
//                    var popup = PopupMenu(it.context, it)
//                    popup.menuInflater?.inflate(R.menu.popup_home_pill_list_edit, popup.menu)
//
//                    popup.setOnMenuItemClickListener {
//                        when (it?.itemId) {
//                            R.id.pill_edit -> {
//                                // 다이얼로그
//                                return@setOnMenuItemClickListener true
//                            }
//                            R.id.pill_delete -> {
//                                return@setOnMenuItemClickListener true
//                            }
//                            R.id.pill_stop -> {
//                                return@setOnMenuItemClickListener true
//
//                            }
//                        }
//                        return@setOnMenuItemClickListener false
//                    }
//                    popup.show()


//            }

            // 약 리스트 체크 & 체크 취소
            binding.ivHomePillListCheck.setOnClickListener {
                binding.ivHomePillListCheck.isSelected = !binding.ivHomePillListCheck.isSelected
            }

            // 약 리스트 수정 클릭시 팝업
//            binding.ivHomePillListEdit.setOnClickListener {
//                var popup = PopupMenu(this, binding.ivHomePillListEdit)
//                menuInflater
//            }






        }
    }
}
