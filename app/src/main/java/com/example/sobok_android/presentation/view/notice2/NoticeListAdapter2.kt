package com.example.sobok_android.presentation.view.notice2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.databinding.ItemNoticeList2Binding
import com.example.sobok_android.domain.model.notice2.NoticeListData2
import com.example.sobok_android.presentation.view.notice2.model.NoticeData

class NoticeListAdapter2 : RecyclerView.Adapter<NoticeListAdapter2.NoticeList2ViewHolder>() {

    private val _noticeList2 = mutableListOf<NoticeListData2.Data.Info>()

    var noticeList2: List<NoticeListData2.Data.Info> = _noticeList2
        set(value) {
            _noticeList2.clear()
            _noticeList2.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoticeList2ViewHolder {
        val binding = ItemNoticeList2Binding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NoticeList2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeList2ViewHolder, position: Int) {
        val item = _noticeList2[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int = _noticeList2.size



    class NoticeList2ViewHolder(private val binding: ItemNoticeList2Binding)
        : RecyclerView.ViewHolder(binding.root) {
            fun onBind(data: NoticeListData2.Data.Info) {
                binding.noticeData = data

                if (data.section.equals("pill")) {
                    if (data.isOkay.equals("waiting")) {
                        binding.noticeMessage = "${data.senderName}님이 약 알림 일정을 보냈어요"
                    }
                    else if (data.isOkay.equals("accept")) {
                        binding.noticeMessage = "${data.senderName}님이 보낸 약 알림 일정을 수락했어요"
                    }
                    else binding.noticeMessage = "${data.senderName}님이 보낸 약 알림 일정을 거절했어요"

                } else if (data.section.equals("calendar")) {
                    if (data.isOkay.equals("waiting")) {
                        binding.noticeMessage = "${data.senderName}님이 친구를 신청했어요"
                    }
                    else if (data.isOkay.equals("accept")) {
                        binding.noticeMessage = "${data.senderName}님의 친구 신청을 수락했어요"
                    }
                    else binding.noticeMessage = "${data.senderName}님의 친구 신청을 거절했어요"
                }


                binding.isPill = data.section.equals("pill")

                binding.isWaiting = data.isOkay.equals("waiting")

                binding.tvNoticeListDetail.isVisible = data.section.equals("pill") && data.isOkay.equals("waiting")
                binding.ivNoticeListDetail.isVisible = data.section.equals("pill") && data.isOkay.equals("waiting")

            }
        }
}