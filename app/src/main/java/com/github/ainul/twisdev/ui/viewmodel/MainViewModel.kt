package com.github.ainul.twisdev.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.ainul.twisdev.network.ItemModel
import com.github.ainul.twisdev.repository.RantingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {

    // setup UI thread scope to run suspend fun & repository controller
    private val uiScope = CoroutineScope(Job() + Dispatchers.Main)
    private val repository = RantingRepository()

    // listItemData holder, used on initialization
    private val _fetchedListItemData = MutableLiveData<List<ItemModel>>()
    val fetchedListItemData: LiveData<List<ItemModel>> get() = _fetchedListItemData

    init {
        uiScope.launch {
            _fetchedListItemData.value = repository.fetchDataFromNetwork()
        }
    }

    /**
     * These piece of code is used for managing actionBar show/hide state.
     * [hideActionBar] can be called to set the state-value to opposite of the current state.
     */
    private val _actionBarHidden = MutableLiveData<Boolean>()
    val actionBarHidden: LiveData<Boolean> get() = _actionBarHidden
    fun hideActionBar() {
        _actionBarHidden.value = _actionBarHidden.value != true
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

    fun updateItem(data: CartItems, position: Int) {
//        listOfItems.forEachIndexed { index, cartItems ->
//            if (cartItems.itemModel.id == data.itemModel.id) {
//                if (data.quantity <= 0) listOfItems.removeAt(index)
//                else listOfItems[index] = data
//            }
//        }

        if (data.quantity <= 0) listOfItems.removeAt(position)
        else listOfItems[position] = data

        _itemsOnCart.value = listOfItems
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

    companion object {
        data class CartItems(val itemModel: ItemModel, var quantity: Int = 1) {
            fun inc() {
                quantity += 1
            }

            fun dec() {
                quantity -= 1
            }
        }
    }
}