package com.github.ainul.twisdev.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.ainul.twisdev.databinding.ListItemLayoutBinding
import com.github.ainul.twisdev.ui.main.adapter.listener.ListItemListener
import com.github.ainul.twisdev.ui.main.viewmodel.MainViewModel.Companion.CartItems
import com.github.ainul.twisdev.util.Constants.IMG_URL

class ListItemDiffUtil : DiffUtil.ItemCallback<CartItems>() {
    override fun areItemsTheSame(oldItem: CartItems, newItem: CartItems): Boolean {
        return oldItem.itemModel.id == newItem.itemModel.id
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
                val path: String = IMG_URL + item.defaultPhoto.imgPath

                if (item.defaultPhoto.imgPath.isNotBlank()) glide.load(path).into(this)
                else glide.clear(this)
            }

            // increase item quantity
            binding.increase.setOnClickListener {
                listItemListener.onListItemAction(data, true)
            }

            // decrease item quantity
            binding.decrease.setOnClickListener {
                listItemListener.onListItemAction(data, false)
            }
        }
    }
}