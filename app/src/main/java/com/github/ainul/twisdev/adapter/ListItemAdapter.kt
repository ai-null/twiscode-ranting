package com.github.ainul.twisdev.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ainul.twisdev.databinding.GridItemLayoutBinding

class ListItemAdapter(private val context: Context) :
    RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {

    var data: List<Int> = ArrayList()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = GridItemLayoutBinding.inflate(inflater)

        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = data[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ListItemViewHolder(private val binding: GridItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Int) {
            binding.data = data
        }
    }
}