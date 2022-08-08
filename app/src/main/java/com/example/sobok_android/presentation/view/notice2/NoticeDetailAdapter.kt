package com.example.sobok_android.presentation.view.notice2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.databinding.ActivityNoticeDetailPillInfoBinding
import com.example.sobok_android.databinding.ItemNoticeDetailPillTimeBinding
import com.example.sobok_android.domain.model.notice2.NoticeDetailData

class NoticeDetailAdapter : RecyclerView.Adapter<NoticeDetailAdapter.NoticeDetailViewHolder>() {

    private val _noticeDetail = mutableListOf<NoticeDetailData>()

    var noticeDetail: List<NoticeDetailData> = _noticeDetail
        set(value) {
            _noticeDetail.clear()
            _noticeDetail.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoticeDetailViewHolder {
        val binding = ItemNoticeDetailPillTimeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NoticeDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeDetailViewHolder, position: Int) {
        holder.onBind(_noticeDetail[position])
    }

    override fun getItemCount(): Int = _noticeDetail.size

    class NoticeDetailViewHolder(val binding: ItemNoticeDetailPillTimeBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun onBind(data: NoticeDetailData) {
                binding.noticeDetailData = data.data.scheduleTime.toString()
            }
        }
}