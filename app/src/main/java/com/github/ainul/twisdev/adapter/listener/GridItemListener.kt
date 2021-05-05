package com.github.ainul.twisdev.adapter.listener

import com.github.ainul.twisdev.network.ItemModel

interface GridItemListener {
    fun addItemToCart(e: ItemModel)
}