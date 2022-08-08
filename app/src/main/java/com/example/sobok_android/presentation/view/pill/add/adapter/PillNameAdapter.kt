package com.example.sobok_android.presentation.view.pill.add.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ItemPillAddPillNameBinding

class PillNameAdapter(
    private val deleteName: ((String) -> Unit)? = null
) : ListAdapter<String, PillNameAdapter.PillNameViewHolder>(
    diffUtil
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillNameViewHolder {
        val binding: ItemPillAddPillNameBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pill_add_pill_name, parent, false
        )
        return PillNameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PillNameAdapter.PillNameViewHolder, position: Int) {
        holder.onBind(getItem(position), deleteName)

    }

    inner class PillNameViewHolder(
        val binding: ItemPillAddPillNameBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(name: String, deleteName: ((String) -> Unit)? = null) {
            if (name == "null") {
                binding.tvPillName.setText("")
            } else {
                binding.tvPillName.setText(name)
            }

//            if (position == 0) {
//                binding.ivClose.visibility = View.GONE
//                isFillPillName = binding.tvPillName.text.isNotEmpty()
//            }

            binding.ivClose.setOnClickListener {
                deleteName?.invoke(name)
                notifyDataSetChanged()
            }

            binding.tvPillName.setOnFocusChangeListener { view, hasFocus ->
                binding.isEditable = hasFocus
            }

            binding.ivEditable.setOnClickListener {
                binding.tvPillName.text = null
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
