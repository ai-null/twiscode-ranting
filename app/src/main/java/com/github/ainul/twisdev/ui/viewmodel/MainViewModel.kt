package com.github.ainul.twisdev.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.ainul.twisdev.repository.RantingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val uiScope = CoroutineScope(Job() + Dispatchers.Main)
    private val repository = RantingRepository()

    init {
        uiScope.launch {
            Log.i("LIST_DATA", "${repository.fetchDataFromNetwork()[0]}")
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
}