package com.github.ainul.twisdev.ui.main.view.adapter.listener

import com.github.ainul.twisdev.ui.main.viewmodel.MainViewModel.Companion.CartItems

interface ListItemListener {
    fun onListItemAction(data: CartItems, increase: Boolean) {
        if (increase) data.inc()
        else data.dec()
    }
}