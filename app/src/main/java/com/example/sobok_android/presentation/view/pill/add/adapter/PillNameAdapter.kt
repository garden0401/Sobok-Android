package com.example.sobok_android.presentation.view.pill.add.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ItemPillAddPillNameBinding

class PillNameAdapter : RecyclerView.Adapter<PillNameAdapter.PillNameViewHolder>() {

    private val _pillNameList = mutableListOf<String>()

    var pillNameList: List<String> = _pillNameList // = : getter의 의미
        set(value) {
            Log.d("pill-list-adapter", "set")
            _pillNameList.clear()
            _pillNameList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillNameViewHolder {
        val binding: ItemPillAddPillNameBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pill_add_pill_name, parent, false
        )
        return PillNameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PillNameAdapter.PillNameViewHolder, position: Int) {
        holder.onBind(_pillNameList[position], position)
    }

    override fun getItemCount(): Int = _pillNameList.size

    inner class PillNameViewHolder(
        val binding: ItemPillAddPillNameBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(nameList: String, position: Int) {
            binding.tvPillName.setText(nameList)

            binding.ivClose.setOnClickListener {
                _pillNameList.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    fun addItem(name: String) {
        _pillNameList.add(name)
        notifyDataSetChanged()
    }
}