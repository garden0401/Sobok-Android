package com.example.sobok_android.presentation.view.pill.add.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ItemPillAddPillTimeBinding

class PillTimeAdapter : RecyclerView.Adapter<PillTimeAdapter.PillTimeViewHolder>() {

    private val _pillTimeList = mutableListOf<String>(
        "오전 8:00",
        "오후 1:00",
        "오후 7:00"
    )

    var pillTimeList: List<String> = _pillTimeList // = : getter의 의미
        set(value) {
            Log.d("pill-list-adapter", "set")
            _pillTimeList.clear()
            _pillTimeList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillTimeViewHolder {
        val binding: ItemPillAddPillTimeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pill_add_pill_time, parent, false
        )
        return PillTimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PillTimeAdapter.PillTimeViewHolder, position: Int) {
        holder.onBind(_pillTimeList[position], position)
    }

    override fun getItemCount(): Int = _pillTimeList.size

    inner class PillTimeViewHolder(
        val binding: ItemPillAddPillTimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(time: String, position: Int) {
            binding.pillTime.text = time

            binding.ivTimeCancel.setOnClickListener {
                _pillTimeList.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    fun makeText(time: String) {
        _pillTimeList.add(time)
        notifyDataSetChanged()
    }

}