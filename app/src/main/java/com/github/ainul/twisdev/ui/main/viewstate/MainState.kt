package com.github.ainul.twisdev.ui.main.viewstate

import com.github.ainul.twisdev.data.model.ItemModel

sealed class MainState {
    object Loading : MainState()
    data class Succeed(val data: List<ItemModel>) : MainState()
    data class Failure(val message: String?) : MainState()
}