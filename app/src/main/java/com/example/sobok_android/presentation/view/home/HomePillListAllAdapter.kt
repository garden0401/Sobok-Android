package com.example.sobok_android.presentation.view.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.databinding.ItemHomePillListAllBinding
import com.example.sobok_android.domain.model.home.PillListData
import com.example.sobok_android.util.DateTimeUtil


class HomePillListAllAdapter : RecyclerView.Adapter<HomePillListAllAdapter.HomePillListAllViewHolder>() {

    private val _homePillListAll= mutableListOf<PillListData.ScheduleInfo>()

    var homePillListAll: List<PillListData.ScheduleInfo> =  _homePillListAll
        set(value) {
            _homePillListAll.clear()
            _homePillListAll.addAll(value)
            notifyDataSetChanged()
        }

    private var _isHome : Boolean = false
    fun setIsHome(value: Boolean) {
        _isHome = value
    }

    // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
    private var stickerClickListener : ((Boolean) -> Unit)? = null

    fun setStickerClickListener(listener: (Boolean) -> Unit) {
        stickerClickListener = listener
    }

    // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
    private var _isEdit : Boolean = true

    fun setIsEdit(value: Boolean) {
        _isEdit = value
        //Log.d("alladpaterEdit","${value}")
        notifyDataSetChanged()
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
        fun onBind(data : PillListData.ScheduleInfo) {
            binding.tvHomePillListTime.text = DateTimeUtil.convertPillListStringToKoreaTime(data.scheduleTime)

            HomePillListDetailAdapter().apply {
                setIsHome(_isHome)
                homePillListDetail = data.scheduleList

                binding.rvHomePillListDetail.adapter = this
                // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
                setStickerClickListener {
                    stickerClickListener?.invoke(it)
                    Log.d("bigAdapter", "true{$it}")
                }

                // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
                setIsEdit(_isEdit)
                Log.d("*****AllAdapter", "${_isEdit}")
            }
        }
    }
}