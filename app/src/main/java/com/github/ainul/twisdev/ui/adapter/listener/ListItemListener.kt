package com.github.ainul.twisdev.ui.adapter.listener

import com.github.ainul.twisdev.ui.viewmodel.MainViewModel.Companion.CartItems

interface ListItemListener {
    fun onListItemAction(data: CartItems, increase: Boolean) {
        if (increase) data.inc()
        else data.dec()
    }
}