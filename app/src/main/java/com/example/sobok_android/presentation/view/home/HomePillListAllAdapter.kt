package com.example.sobok_android.presentation.view.home

import android.view.LayoutInflater
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

    class HomePillListAllViewHolder(private val binding : ItemHomePillListAllBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : HomePillListData) {
            binding.tvHomePillListTime.text = data.scheduleTime

            HomePillListDetailAdapter().apply {
                homePillListDetail = data.scheduleList
                binding.rvHomePillListDetail.adapter = this

            }
        }
    }
}