package com.github.ainul.twisdev.ui.main.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.*
import com.github.ainul.twisdev.data.model.ItemModel
import com.github.ainul.twisdev.data.repository.RantingRepository
import com.github.ainul.twisdev.ui.main.intent.MainIntent
import com.github.ainul.twisdev.ui.main.viewstate.MainState
import com.github.ainul.twisdev.util.Util
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel constructor(private val repository: RantingRepository) : ViewModel() {

    // listItemData holder, used on initialization
    private val _fetchedListItemData = MutableLiveData<MainState>()
    val fetchedListItemData: LiveData<MainState> get() = _fetchedListItemData

    // user intent
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    init {
        refresh()
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.Refresh -> refresh()
                    is MainIntent.AddItemToCart -> addItemToCart(it.item)
                }
            }
        }
    }

    private fun refresh() {
        _fetchedListItemData.value = MainState.Loading
        viewModelScope.launch {
            try {
                repository.fetchDataFromNetwork().also {
                    _fetchedListItemData.value = MainState.Succeed(it)
                }
            } catch (e: Exception) {
                _fetchedListItemData.value = MainState.Failure(e.message)
            }
        }
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
     * it takes from [_listOfItems] every time it update it'll also update value of [_itemsOnCart].
     * since there's no database in this project this is the least I can do
     */
    private val _itemsOnCart = MutableLiveData<List<CartItems>>()
    val itemsOnCart: LiveData<List<CartItems>> get() = _itemsOnCart

    private val _listOfItems = mutableListOf<CartItems>()

    private fun addItemToCart(item: ItemModel) {
        if (!isItemAlreadyAdded(item)) {
            _listOfItems.add(CartItems(item))
            updatePrice(item.price.toInt())
        }
    }

    fun getCartItems() {
        _itemsOnCart.value = _listOfItems
    }

    /**
     * total price data holder,
     * for the public variable it transform Int to a currencyFormatted string
     * then show it to the UI.
     */
    private val _totalPrice = MutableLiveData(0)
    val totalPrice: LiveData<String>
        get() = Transformations.map(_totalPrice) {
            Util.currencyFormatter(it.toString())
        }

    fun updateData(data: CartItems, increase: Boolean) {
        if (data.quantity.get() <= 0) {
            removeItem(data)
        } else {
            updatePrice(data.itemModel.price.toInt(), increase)
        }
    }

    private fun removeItem(data: CartItems) {
        val iterator = _listOfItems.iterator()

        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.itemModel.id == data.itemModel.id) {
                iterator.remove().also {
                    _itemsOnCart.value = _listOfItems
                    updatePrice(data.itemModel.price.toInt(), false)
                }
            }
        }
    }

    /**
     * update total price
     * it takes [increase] as conditional parameter to choose
     * the data will increased or decreased
     */
    private fun updatePrice(price: Int, increase: Boolean = true) {
        if (price.toString().isNotBlank()) {
            val payload = _totalPrice.value!!.run {
                if (increase) plus(price)
                else minus(price)
            }

            _totalPrice.value = payload
        }
    }

    /**
     * Check whether item is already added on the cart or not.
     * returns true if it added
     */
    private fun isItemAlreadyAdded(item: ItemModel): Boolean {
        var added = false
        _listOfItems.forEach {
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