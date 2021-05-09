package com.github.ainul.twisdev.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.github.ainul.twisdev.network.ItemModel
import com.github.ainul.twisdev.repository.RantingRepository
import com.github.ainul.twisdev.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(app: Application) : AndroidViewModel(app) {

    // setup UI thread scope to run suspend fun & repository controller
    private val uiScope = CoroutineScope(Job() + Dispatchers.Main)
    private val repository = RantingRepository()

    // listItemData holder, used on initialization
    private val _fetchedListItemData = MutableLiveData<ViewState>()
    val fetchedListItemData: LiveData<ViewState> get() = _fetchedListItemData

    fun refresh() {
        _fetchedListItemData.value = ViewState.Loading
        uiScope.launch {
            try {
                repository.fetchDataFromNetwork().also {
                    _fetchedListItemData.value = ViewState.Succeed(it)
                }
            } catch (e: Exception) {
                _fetchedListItemData.value = ViewState.Failure(e.message)
            }
        }
    }

    init {
        refresh()
    }

    /**
     * These piece of code is used for managing actionBar show/hide state.
     * [hideActionBar] can be called to set the state-value to opposite of the current state.
     */
    private val _actionBarHidden = MutableLiveData<Boolean>()
    val actionBarHidden: LiveData<Boolean> get() = _actionBarHidden
    fun hideActionBar(action: Boolean = true) {
        _actionBarHidden.value = action
    }

    /**
     * items liveData holder,
     * it takes from [listOfItems] every time it update it'll also update value of [_itemsOnCart].
     * since there's no database in this project this is the least I can do
     */
    private val _itemsOnCart = MutableLiveData<List<CartItems>>()
    val itemsOnCart: LiveData<List<CartItems>> get() = _itemsOnCart
    var listOfItems = arrayListOf<CartItems>()
        private set

    fun addItemToCart(item: ItemModel) {
        if (!isItemAlreadyAdded(item)) {
            listOfItems.add(CartItems(item))
        }
    }

    /**
     * when quantity of item changes it call [updateData]
     *
     *
     * total price data holder,
     * for the public variable it transform Int to a currencyFormatted string
     * then show it to the UI.
     */
    private val _totalPrice = MutableLiveData<Int>()
    val totalPrice: LiveData<String>
        get() = Transformations.map(_totalPrice) {
            Util.currencyFormatter(it.toString())
        }

    fun updateData(data: CartItems) {
        val iterator = listOfItems.iterator()

        if (data.quantity.get() <= 0) {
            while (iterator.hasNext()) {
                val item = iterator.next()
                if (item.itemModel.id == data.itemModel.id) {
                    iterator.remove()
                    _itemsOnCart.value = listOfItems
                }
            }
        }
    }

    private fun updateTotalPrice(e: Int) {
        _totalPrice.value = e
    }

    /**
     * Check whether item is already added on the cart or not.
     * returns true if yes
     */
    private fun isItemAlreadyAdded(item: ItemModel): Boolean {
        var added = false
        listOfItems.forEach {
            if (it.itemModel == item) added = true
        }

        return added
    }

    /**
     * Item cart data holder,
     * update quantity to ObservableInteger so it can tell the UI on change
     */
    companion object {
        data class CartItems(
            val itemModel: ItemModel,
            val quantity: ObservableInt = ObservableInt(1)
        ) {
            fun inc() {
                quantity.set(quantity.get().plus(1))
                quantity.notifyChange()
            }

            fun dec() {
                quantity.set(quantity.get().minus(1))
                quantity.notifyChange()
            }
        }
    }
}

sealed class ViewState() {
    object Loading : ViewState()
    data class Succeed(val data: List<ItemModel>) : ViewState()
    data class Failure(val message: String?) : ViewState()
}