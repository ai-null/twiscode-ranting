package com.github.ainul.twisdev.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.ainul.twisdev.databinding.ListItemLayoutBinding
import com.github.ainul.twisdev.network.ItemModel

class ListItemAdapter(private val context: Context) :
    RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {
    var data: List<ItemModel> = arrayListOf()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = ListItemLayoutBinding.inflate(inflater, parent, false)

        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ListItemViewHolder(private val binding: ListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ItemModel) {
            binding.data = data
            binding.thumbnail.run {
                val glide = Glide.with(this)
                val path = "https://ranting.twisdev.com/uploads/${data.defaultPhoto.imgPath}"

                if (data.defaultPhoto.imgPath.isNotBlank()) glide.load(path).into(this)
                else glide.clear(this)
            }
        }
    }
}