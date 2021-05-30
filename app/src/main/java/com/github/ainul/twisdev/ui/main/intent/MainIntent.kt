package com.github.ainul.twisdev.ui.main.intent

import com.github.ainul.twisdev.data.model.ItemModel

sealed class MainIntent {
    data class AddItemToCart(val item: ItemModel): MainIntent()
    object Refresh: MainIntent()
}