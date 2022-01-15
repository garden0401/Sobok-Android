package com.example.sobok_android.presentation.view.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.databinding.ItemNoticeListBinding
import com.example.sobok_android.domain.model.notice.NoticeListData
import com.example.sobok_android.presentation.view.notice.model.NoticeInfoData

class NoticeListAdapter : RecyclerView.Adapter<NoticeListAdapter.NoticeListViewHolder>() {

    private val _noticeList = mutableListOf<NoticeInfoData>()

    var noticeList: List<NoticeInfoData> =  _noticeList
        set(value) {
            _noticeList.clear()
            _noticeList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoticeListViewHolder {
        val binding = ItemNoticeListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NoticeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeListViewHolder, position: Int) {
        holder.onBind(_noticeList[position])
    }

    override fun getItemCount(): Int = _noticeList.size

    class NoticeListViewHolder(private val binding : ItemNoticeListBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : NoticeInfoData) {
            binding.noticeInfoData = data
        }
    }
}