package com.example.sobok_android.presentation.view.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.databinding.ItemHomePillListAllBinding


class HomePillListAllAdapter : RecyclerView.Adapter<HomePillListAllAdapter.HomePillListAllViewHolder>() {

    private val _homePillListAll= mutableListOf<HomePillListData>()

    var homePillListAll: List<HomePillListData> =  _homePillListAll
        set(value) {
            _homePillListAll.clear()
            _homePillListAll.addAll(value)
            notifyDataSetChanged()
        }

    private var stickerClickListener : ((Boolean) -> Unit)? = null

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
    ): HomePillListAllViewHolder {
        val binding = ItemHomePillListAllBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return HomePillListAllViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomePillListAllViewHolder, position: Int) {
        holder.onBind(_homePillListAll[position])
    }

    override fun getItemCount(): Int = _homePillListAll.size

    inner class HomePillListAllViewHolder(private val binding : ItemHomePillListAllBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : HomePillListData) {
            binding.tvHomePillListTime.text = data.scheduleTime

            HomePillListDetailAdapter().apply {
                homePillListDetail = data.scheduleList
                binding.rvHomePillListDetail.adapter = this
                setStickerClickListener {
                    stickerClickListener?.invoke(it)
                    Log.d("bigAdapter", "true{$it}")
                }

                // 홈(메인) 약 리스트 수정시 context 버튼 클릭-popup menu 띄우기 (고차함수 써보기)
                setEditContextClickListener { isClick, View ->
                    editContextClickListener?.invoke(isClick, View)
                }

            }
        }
    }
}