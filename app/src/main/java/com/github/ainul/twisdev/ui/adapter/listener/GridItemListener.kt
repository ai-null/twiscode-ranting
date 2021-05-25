package com.github.ainul.twisdev.ui.adapter.listener

import com.github.ainul.twisdev.data.model.ItemModel

interface GridItemListener {
    fun addItemToCart(e: ItemModel)
}