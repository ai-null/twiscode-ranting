package com.github.ainul.twisdev.ui.main.adapter.listener

import com.github.ainul.twisdev.data.model.ItemModel

interface GridItemListener {
    fun addItemToCart(e: ItemModel)
}