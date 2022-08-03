package com.example.sobok_android.presentation.view.noticeSecond

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.databinding.ItemNoticeList2Binding
import com.example.sobok_android.presentation.view.noticeSecond.model.NoticeData

class NoticeListAdapter2 : RecyclerView.Adapter<NoticeListAdapter2.NoticeList2ViewHolder>() {

    val noticeList2 = mutableListOf<NoticeData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): NoticeList2ViewHolder {
        val binding = ItemNoticeList2Binding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NoticeList2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeList2ViewHolder, position: Int) {
        holder.onBind(noticeList2[position])
    }

    override fun getItemCount(): Int = noticeList2.size



    class NoticeList2ViewHolder(private val binding: ItemNoticeList2Binding)
        : RecyclerView.ViewHolder(binding.root) {
            fun onBind(data : NoticeData) {
                binding.noticeData = data.data.infoList

                if (data.data.infoList.section.equals("pill")) {
                    if (data.data.infoList.isOkay.equals("waiting")) {
                        binding.noticeMessage = "${data.data.infoList.senderName}님이 약 알림 일정을 보냈어요"
                    }
                    else if (data.data.infoList.isOkay.equals("accept")) {
                        binding.noticeMessage = "${data.data.infoList.senderName}님이 보낸 약 알림 일정을 수락했어요"
                    }
                    else binding.noticeMessage = "${data.data.infoList.senderName}님이 보낸 약 알림 일정을 거절했어요"

                } else if (data.data.infoList.section.equals("calendar")) {
                    if (data.data.infoList.isOkay.equals("waiting")) {
                        binding.noticeMessage = "${data.data.infoList.senderName}님이 친구를 신청했어요"
                    }
                    else if (data.data.infoList.isOkay.equals("accept")) {
                        binding.noticeMessage = "${data.data.infoList.senderName}님의 친구 신청을 수락했어요"
                    }
                    else binding.noticeMessage = "${data.data.infoList.senderName}님의 친구 신청을 거절했어요"
                }


                binding.isPill = data.data.infoList.section.equals("pill")

                binding.isWaiting = data.data.infoList.isOkay.equals("waiting")

                binding.tvNoticeListDetail.isVisible = data.data.infoList.section.equals("pill") && data.data.infoList.isOkay.equals("waiting")
                binding.ivNoticeListDetail.isVisible = data.data.infoList.section.equals("pill") && data.data.infoList.isOkay.equals("waiting")

            }
        }
}