package com.github.ainul.twisdev.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ainul.twisdev.databinding.GridItemLayoutBinding

class GridItemAdapter(private val context: Context) :
    RecyclerView.Adapter<GridItemAdapter.GridItemViewHolder>() {

    var data: List<Int> = ArrayList()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = GridItemLayoutBinding.inflate(inflater)

        return GridItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        val item = data[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class GridItemViewHolder(private val binding: GridItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Int) {
            binding.data = data
        }
    }
}