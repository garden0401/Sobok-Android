package com.example.sobok_android.presentation.view.pill.add.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ItemPillAddPillNameBinding

class PillNameAdapter : RecyclerView.Adapter<PillNameAdapter.PillNameViewHolder>() {

    private var _pillNameList = mutableListOf<String>()
    val realPillNameList = mutableListOf<String>()
    var isFillPillName: Boolean = false

    var pillNameList: List<String> = _pillNameList // = : getter의 의미
        set(value) {
            Log.d("pill-list-adapter", "set")
            Log.d("real-pill-list111", "$realPillNameList")
                _pillNameList.clear()
                _pillNameList.addAll(value)
                realPillNameList.clear()
                realPillNameList.addAll(_pillNameList)

            Log.d("pill-list", "$_pillNameList")
            Log.d("real-pill-list", "$realPillNameList")
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
        Log.d("onbind-viewHolder", "$position")
        holder.setIsRecyclable(false)
        holder.onBind(realPillNameList[position], position)

    }

    override fun getItemCount(): Int = realPillNameList.size

    inner class PillNameViewHolder(
        val binding: ItemPillAddPillNameBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(nameList: String, position: Int) {
            if (nameList == "null") {
                binding.tvPillName.setText("")
            } else {
                binding.tvPillName.setText(nameList)
            }

            binding.tvPillName.addTextChangedListener {
                realPillNameList[position] = it.toString()
            }

            if(position == 0) {
                binding.ivClose.visibility = View.GONE
                isFillPillName = binding.tvPillName.text.isNotEmpty()
            }

            binding.ivClose.setOnClickListener {
                Log.d("before delete item", "${realPillNameList[position]}")
                deleteItem(position)
                Log.d("after delete item", "${realPillNameList[position]}")
            }

            binding.tvPillName.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    binding.isEditable = true
                } else {
                    binding.isEditable = false
                }
            }

            binding.ivEditable.setOnClickListener {
                binding.tvPillName.text = null
            }
        }
    }
    fun deleteItem(position: Int){
        val _position = position
        Log.d("###real item list", "${realPillNameList}")
        Log.d("###pill item list", "${_pillNameList}")
        realPillNameList.removeAt(_position)
        _pillNameList.removeAt(_position)
        //_pillNameList = realPillNameList
        //_pillNameList.removeAt(_position)
        Log.d("#####real item list", "${realPillNameList}")
        Log.d("#####pill item list", "${_pillNameList}")

        notifyDataSetChanged()
    }
}
