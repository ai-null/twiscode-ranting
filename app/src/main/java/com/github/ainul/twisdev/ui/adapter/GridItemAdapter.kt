package com.github.ainul.twisdev.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.ainul.twisdev.ui.adapter.listener.GridItemListener
import com.github.ainul.twisdev.databinding.GridItemLayoutBinding
import com.github.ainul.twisdev.data.model.ItemModel
import com.github.ainul.twisdev.util.Constants.IMG_URL

class GridItemAdapter(private val context: Context, private val listener: GridItemListener) :
    RecyclerView.Adapter<GridItemAdapter.GridItemViewHolder>() {

    var data: List<ItemModel> = ArrayList()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = GridItemLayoutBinding.inflate(inflater)

        return GridItemViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    class GridItemViewHolder(
        private val binding: GridItemLayoutBinding,
        private val listener: GridItemListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Bind data [ItemModel] to xml instead of assign the value manually,
         * except few components that need to show differently based on the data
         */
        fun bind(data: ItemModel) {
            binding.data = data
            // show thumbnail if there's one, otherwise clear image
            binding.thumbnail.run {
                val glide = Glide.with(this)
                val path: String = IMG_URL + data.defaultPhoto.imgPath

                if (data.defaultPhoto.imgPath.isNotBlank()) glide.load(path).into(this)
                else glide.clear(this)
            }
            // halalStamp product filter
            binding.halalStamp.run {
                visibility = if (data.isHalal == "1") View.VISIBLE else View.INVISIBLE
            }
            // add item to cart
            binding.button.setOnClickListener {
                listener.addItemToCart(data)
            }
        }
    }
}