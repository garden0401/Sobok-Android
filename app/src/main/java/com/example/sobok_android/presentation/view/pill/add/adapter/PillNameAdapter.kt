package com.example.sobok_android.presentation.view.pill.add.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ItemPillAddPillNameBinding

class PillNameAdapter : RecyclerView.Adapter<PillNameAdapter.PillNameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillNameViewHolder {
        val binding: ItemPillAddPillNameBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pill_add_pill_name, parent, false
        )
        return PillNameViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PillNameAdapter.PillNameViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class PillNameViewHolder(
        val binding: ItemPillAddPillNameBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    }

}