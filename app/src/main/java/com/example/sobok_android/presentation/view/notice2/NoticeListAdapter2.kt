package com.example.sobok_android.presentation.view.notice2

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ItemNoticeList2Binding
import com.example.sobok_android.domain.model.notice2.NoticeListData2
import com.example.sobok_android.presentation.view.notice2.model.NoticeData
import com.example.sobok_android.presentation.view.notice2.viewmodel.NoticeViewModel2
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel

class NoticeListAdapter2 : RecyclerView.Adapter<NoticeListAdapter2.NoticeList2ViewHolder>() {

    // isOkay값 -> NoticeFragment 이동을 위한 고차함수
    private var isOkay : ((String, Int) -> Unit)? = null

    fun setIsOkay(listener: (String, Int) -> Unit) {
        isOkay = listener
    }

    // 리스트 클릭 interface
    interface ItemClick {
        fun onClick(view: View, position: Int, noticeId: Int, pillId: Int)
    }

    var itemClick: ItemClick? = null

//    // sendGroupId값 -> NoticeFragment로 전달
//    interface CalendarConfirm {
//        fun onClick(view: View, position: Int, sendGroupId: Int)
//    }
//
//    var calendarConfirm: CalendarConfirm? = null


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

        //리스트 클릭 관련
        if (itemClick != null) {
            holder.binding.clNoticeListDetail.setOnClickListener {
                itemClick?.onClick(it, position, item.noticeId, item.pillId!!)
             }
        }

        // sendGroupId값 -> NoticeFragment로 전달
//        if (calendarConfirm != null) {
//            holder.binding.btnNoticeAccept.setOnClickListener {
//                calendarConfirm?.onClick(it, position, item.senderGroupId!!)
//            }
//            holder.binding.btnNoticeReject.setOnClickListener {
//                calendarConfirm?.onClick(it, position, item.senderGroupId!!)
//            }
//        }
    }

    override fun getItemCount(): Int = _noticeList2.size



    inner class NoticeList2ViewHolder(val binding: ItemNoticeList2Binding)
        : RecyclerView.ViewHolder(binding.root) {
            fun onBind(data: NoticeListData2.Data.Info) {
                binding.noticeData = data

                // 알림 정보 전체 조회 문구&상태
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


                // 친구 신청&약 일정 거절 다이어로그

//                var noticeFragment2 = NoticeFragment2()
//                var bundle = Bundle()
//                bundle.putInt("sendGroupId", data.senderGroupId!!.toInt())
//                noticeFragment2.childFragmentManager!!.beginTransaction().replace(R.id.noticeFragment, noticeFragment2).commit()

                binding.btnNoticeReject.setOnClickListener {
                    val builder = AlertDialog.Builder(it.context)
                    if (data.section.equals("calendar")) {
                        builder.setTitle("${data.senderName}님의 친구 신청을 거절할까요?")
                            .setMessage("거절하면 상대방이 내 캘린더를 볼 수 없어요")
                            .setPositiveButton("확인") {dialog, id ->
                                // 캘린더 공유 거절하기 서버 연결 및 알림리스트 새로고침
                                // isOkay 에 refuse
                                isOkay?.invoke("refuse",data.senderGroupId!!)


                            }
                            .setNegativeButton("취소") {dialog, id ->
                                dialog.cancel()
                            }
                        builder.show()
                    }
                    else
                        builder.setTitle("이 약을 거절할까요?")
                            .setMessage("거절하면 해당 약 알림을 받을 수 없어요")
                            .setPositiveButton("확인") {dialog, id ->
                                // 약 일정 거절하기 서버 연결 및 알림리스트 새로고침
                                // isOkay 에 refuse
                                isOkay?.invoke("refuse",data.pillId!!)
                            }
                            .setNegativeButton("취소") {dialog, id ->
                                dialog.cancel()
                            }
                    builder.show()
                }

                // 친구 신청&약 일정 수락 다이어로그
                binding.btnNoticeAccept.setOnClickListener {
                    val builder = AlertDialog.Builder(it.context)
                    if (data.section.equals("calendar")) {
                        builder.setTitle("${data.senderName}님의 친구 신청을 수락할까요?")
                            .setMessage("수락하면 상대방이 내 캘린더를 볼 수 있어요")
                            .setPositiveButton("확인") {dialog, id ->
                                // 캘린더 공유 수락하기 서버 연결 및 알림리스트 새로고침
                                // isOkay 에 accept
                                isOkay?.invoke("accept", data.senderGroupId!!)

                            }
                            .setNegativeButton("취소") {dialog, id ->
                                dialog.cancel()
                            }
                        builder.show()
                    }
                    else
                        builder.setTitle("이 약을 수락할까요?")
                            .setMessage("수락하면 홈 캘린더에 약이 추가되고, 정해진 시간에 알림을 받을 수 있어요")
                            .setPositiveButton("확인") {dialog, id ->
                                // 약 일정 수락하기 서버 연결 및 알림리스트 새로고침
                                // isOkay 에 accept
                                isOkay?.invoke("accept",data.pillId!!)
                            }
                            .setNegativeButton("취소") {dialog, id ->
                                dialog.cancel()
                            }
                    builder.show()
                }


            }
        }
}