package com.example.sobok_android.presentation.view.myinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.databinding.ItemMyInfoPillBinding
import com.example.sobok_android.domain.model.myinfo.MyPillData

class MyPillManageAdapter : RecyclerView.Adapter<MyPillManageAdapter.MyPillManageViewHolder>() {

    private val _myPillList = mutableListOf<MyPillData>()
    private var onItemClickListener: ((String, String, String) -> Unit)? = null

    var myPillList: List<MyPillData> = _myPillList
        set(value) {
            _myPillList.clear()
            _myPillList.addAll(value)
            notifyDataSetChanged()
        }

    fun setOnItemClickListener(listener: (String, String, String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPillManageViewHolder {
        val binding = ItemMyInfoPillBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyPillManageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPillManageViewHolder, position: Int) {
        holder.onBind(_myPillList[position])
//        holder.itemView.setOnClickListener {
//            if(_searchResultList[position].status == "NONE"){
//                onItemClickListener?.invoke(
//                    _searchResultList[position].id,
//                    _searchResultList[position].name,
//                    _searchResultList[position].address,
//                )
//            }
//        }
    }

    override fun getItemCount(): Int = _myPillList.size

    inner class MyPillManageViewHolder(
        private val binding: ItemMyInfoPillBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(myPillData: MyPillData) {
            with(binding) {
                myPillInfoData = myPillData
            }
        }
    }
}