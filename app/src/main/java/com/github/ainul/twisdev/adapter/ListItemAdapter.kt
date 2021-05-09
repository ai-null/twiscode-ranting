package com.github.ainul.twisdev.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.ainul.twisdev.adapter.listener.ListItemListener
import com.github.ainul.twisdev.databinding.ListItemLayoutBinding
import com.github.ainul.twisdev.ui.viewmodel.MainViewModel.Companion.CartItems

class ListItemDiffUtil : DiffUtil.ItemCallback<CartItems>() {
    override fun areItemsTheSame(oldItem: CartItems, newItem: CartItems): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CartItems, newItem: CartItems): Boolean {
        return oldItem == newItem
    }
}

class ListItemAdapter(
    private val context: Context,
    private val listItemListener: ListItemListener
) : ListAdapter<CartItems, ListItemAdapter.ListItemViewHolder>(ListItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = ListItemLayoutBinding.inflate(inflater, parent, false)
        return ListItemViewHolder(view, listItemListener)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ListItemViewHolder(
        private val binding: ListItemLayoutBinding,
        private val listItemListener: ListItemListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CartItems) {
            binding.data = data
            binding.quantity.text = data.quantity.toString()
            binding.thumbnail.run {
                val item = data.itemModel
                val glide = Glide.with(this)
                val path = "https://ranting.twisdev.com/uploads/${item.defaultPhoto.imgPath}"

                if (item.defaultPhoto.imgPath.isNotBlank()) glide.load(path).into(this)
                else glide.clear(this)
            }

            // increase item quantity
            binding.increase.setOnClickListener {
                data.inc()
                listItemListener.onListItemAction(data)
            }

            // decrease item quantity
            binding.decrease.setOnClickListener {
                data.dec()
                listItemListener.onListItemAction(data)
            }
        }
    }
}