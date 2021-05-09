package com.github.ainul.twisdev.adapter.listener

import com.github.ainul.twisdev.ui.viewmodel.MainViewModel.Companion.CartItems

interface ListItemListener {
    fun onListItemAction(data: CartItems)
}