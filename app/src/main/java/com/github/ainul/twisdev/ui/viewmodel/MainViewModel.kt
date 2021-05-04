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

    private val _itemsOnCart = MutableLiveData<List<ItemModel>>()
    val itemsOnCart: LiveData<List<ItemModel>> get() = _itemsOnCart
    var listOfItems = arrayListOf<ItemModel>()
        private set(value) {
            _itemsOnCart.value = value
            field = value
        }

    fun addItemToCart(e: ItemModel) { listOfItems.add(e) }
    fun removeItemFromCart(e: ItemModel) { listOfItems.remove(e) }
}