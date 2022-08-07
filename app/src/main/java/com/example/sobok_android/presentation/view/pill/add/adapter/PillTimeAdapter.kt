package com.example.sobok_android.presentation.view.pill.add.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ItemPillAddPillTimeBinding

class PillTimeAdapter(
    private val deleteItem: ((String) -> Unit)? = null
) : ListAdapter<String, PillTimeAdapter.PillTimeViewHolder>(
    diffUtil
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillTimeViewHolder {
        val binding: ItemPillAddPillTimeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pill_add_pill_time, parent, false
        )
        return PillTimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PillTimeAdapter.PillTimeViewHolder, position: Int) {
        holder.onBind(getItem(position), deleteItem)
    }

    inner class PillTimeViewHolder(
        val binding: ItemPillAddPillTimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(time: String, deleteItem: ((String) -> Unit)? = null) {
            binding.pillTime.text = time
            binding.ivTimeCancel.setOnClickListener {
                deleteItem?.invoke(time)
                notifyDataSetChanged()
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areContentsTheSame(oldItem: String, newItem: String) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: String, newItem: String) =
                oldItem == newItem
        }
    }
}
