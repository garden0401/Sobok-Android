package com.example.sobok_android.presentation.view.pill.add.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ItemPillAddPillInfoBinding
import com.example.sobok_android.domain.model.pill.pilladd.PillListData

class PillListAdapter : RecyclerView.Adapter<PillListAdapter.PillListViewHolder>() {
    private var _pillList = mutableListOf<PillListData.PillInfo>()
    var pillList: MutableList<PillListData.PillInfo> = _pillList // = : getter의 의미
        set(value) {
            _pillList.clear()
            _pillList.addAll(value)
            notifyDataSetChanged()
        }

    // 형식만 만들고 , 일은 밖에서 하는 용
    private var deletePillItemListener: ((Int) -> Unit)? = null

    // 받은 listener 함수를 위에 형식만 만든 함수에 할당해야함.
    fun setDeletePillItemListener(listener: ((Int) -> Unit)) {
        deletePillItemListener = listener
    }

    private var pillListCountListner: ((Int) -> Unit)? = null
    fun setPillListCountListerner(listener: ((Int) -> Unit)) {
        pillListCountListner = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillListViewHolder {
        val binding: ItemPillAddPillInfoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pill_add_pill_info,
            parent,
            false
        )
        return PillListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PillListViewHolder, position: Int) {
        holder.onBind(_pillList[position], position)
    }

    override fun getItemCount(): Int = _pillList.size

    inner class PillListViewHolder(
        val binding: ItemPillAddPillInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PillListData.PillInfo, position: Int) {
            binding.pillInfo = data

            binding.ivClose.setOnClickListener {
                _pillList.removeAt(position)
                // 고차함수 호출할 곳
                pillListCountListner?.invoke(itemCount)
                notifyDataSetChanged()

                deletePillItemListener?.invoke(position)
            }
        }
    }
}
