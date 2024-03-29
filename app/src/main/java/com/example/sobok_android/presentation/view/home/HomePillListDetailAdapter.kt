package com.example.sobok_android.presentation.view.home

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ItemHomePillListDetailBinding
import com.example.sobok_android.domain.model.home.PillListData.ScheduleInfo.Schedule


class HomePillListDetailAdapter :
    RecyclerView.Adapter<HomePillListDetailAdapter.HomePillListDetailViewHolder>() {

    private val _homePillListDetail = mutableListOf<Schedule>()

    var homePillListDetail: List<Schedule> = _homePillListDetail
        set(value) {
            _homePillListDetail.clear()
            _homePillListDetail.addAll(value)
            notifyDataSetChanged()
        }

    private var _isHome : Boolean = false
    fun setIsHome(value: Boolean) {
        _isHome = value
        Log.d("isHomeDetail", "${_isHome}")
    }

    // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
    private var stickerClickListener: ((Boolean) -> Unit)? = null

    fun setStickerClickListener(listener: (Boolean) -> Unit) {
        stickerClickListener = listener
    }

     //홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
    private var _isEdit : Boolean = true

    fun setIsEdit(value: Boolean) {
        _isEdit = value
        //Log.d("detailedit" , "${value}")
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

    inner class HomePillListDetailViewHolder(private val binding: ItemHomePillListDetailBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Schedule) {

            binding.pillInfo = data
            binding.ivHomePillListCheck.isSelected = data.isCheck

            with(binding) {
                when(_isHome) {
                    true -> {
                        isHome = true

                        // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
                        binding.isEdit = _isEdit

                        // 약 리스트 체크 & 체크 취소
                        val check = binding.ivHomePillListCheck
                        check.setOnClickListener {
                            when (check.isSelected) {
                                true -> {
                                    val builder = AlertDialog.Builder(it.context)
                                    builder.setTitle("복약하지 않은 약인가요?")
                                        .setMessage("복약을 취소하면 소중한 사람들의 응원도 같이 삭제되어요.")
                                        .setPositiveButton("복약 취소") { dialog, id ->
                                            // 복약 취소 행동
                                        }
                                        .setNegativeButton("취소"
                                        ) { dialog, id ->
                                            dialog.cancel()
                                        }
                                    builder.show()

                                }
                                else -> {check.isSelected = true}
                            }
                        }


                        //약 리스트 수정 클릭시 팝업
                        binding.ivHomePillListEdit.setOnClickListener {view->
                            var popup = PopupMenu(view.context, view)
                            popup.menuInflater?.inflate(R.menu.popup_home_pill_list_edit, popup.menu)

                            popup.setOnMenuItemClickListener {
                                when (it?.itemId) {
                                    R.id.pill_edit -> {
                                        //약수정 intent로 약수정 뷰로 넘기기

                                        return@setOnMenuItemClickListener true
                                    }
                                    R.id.pill_stop -> {
                                        //복약 중단 다이어로그
                                        val builder = AlertDialog.Builder(view.context)
                                        builder.setTitle("정말로 복약을 중단하나요?")
                                            .setMessage("복약을 중단하면 내일부터 약 알림이 오지 않아요.")
                                            .setPositiveButton("복약 중단") { dialog, id ->
                                                //클릭리스너

                                            }
                                            .setNegativeButton("취소"
                                            ) { dialog, id ->
                                                dialog.cancel()
                                            }
                                        builder.show()

                                        return@setOnMenuItemClickListener true
                                    }
                                    R.id.pill_delete -> {
                                        //약 삭제 다이어로그
                                        val builder = AlertDialog.Builder(view.context)
                                        builder.setTitle("정말로 약을 삭제하나요?")
                                            .setMessage("해당 약에 대한 전체 복약 기록이 사라지고 알림도 오지 않아요.")
                                            .setPositiveButton("삭제") { dialog, id ->
                                                //클릭리스너

                                            }
                                            .setNegativeButton("취소"
                                            ) { dialog, id ->
                                                dialog.cancel()
                                            }
                                        builder.show()

                                        return@setOnMenuItemClickListener true

                                    }
                                }
                                return@setOnMenuItemClickListener false
                            }
                            popup.show()
                        }
                    }
                    else -> {
                        isHome = false
                        //sticker send
                        ivShareStickerSend.isSelected = data.isLikedSchedule
                        ivShareStickerSend.setOnClickListener {
                            if(!ivShareStickerSend.isSelected) {
                                //TODO:스티커 보내는 바텀시트 연결
                                ivShareStickerSend.isSelected = true
                            }
                        }
                    }
                }
            }


            //TODO: 스티커 Id 바텀시트로 같이 보낼 것!
            val stickerTotalCount = data.stickerTotalCount

            //스티커 넣기
            with(binding) {
                if(stickerTotalCount == 0) {
                    ivHomePillListStickerOne.visibility = View.GONE
                    clStickerList.visibility = View.GONE
                }
                if (stickerTotalCount > 0) {
                    stickerOne = data.stickerId[0].stickerId
                    clStickerList.visibility = android.view.View.VISIBLE
                    ivHomePillListStickerOne.visibility = View.VISIBLE
                    ivHomePillListStickerOne.setOnClickListener {
                        stickerClickListener?.invoke(true)
                    }
                }
                if (stickerTotalCount > 1) {
                    stickerTwo = data.stickerId[1].stickerId
                    ivHomePillListStickerTwo.visibility = View.VISIBLE
                    ivHomePillListStickerTwo.setOnClickListener {
                        stickerClickListener?.invoke(true)
                    }
                }
                if (stickerTotalCount > 2) {
                    stickerThree = data.stickerId[2].stickerId
                    ivHomePillListStickerThree.visibility = View.VISIBLE
                    ivHomePillListStickerThree.setOnClickListener {
                        stickerClickListener?.invoke(true)
                    }
                }
                if (stickerTotalCount > 3) {
                    stickerFour = data.stickerId[3].stickerId
                    ivHomePillListStickerFour.visibility = View.VISIBLE
                    ivHomePillListStickerFour.setOnClickListener {
                        stickerClickListener?.invoke(true)
                    }
                }
                if(stickerTotalCount <= 4) {
                    tvHomePillListStickerCount.visibility = View.GONE
                    tvHomePillListPlus.visibility = View.GONE
                }else {
                    tvHomePillListStickerCount.visibility = View.VISIBLE
                    tvHomePillListPlus.visibility = View.VISIBLE
                }
            }

            // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
//            binding.tvHomePillListPillName.setOnClickListener {
//                stickerClickListener?.invoke(true)
//                Log.d("tvtv", "click")
//            }

        }
    }
}
