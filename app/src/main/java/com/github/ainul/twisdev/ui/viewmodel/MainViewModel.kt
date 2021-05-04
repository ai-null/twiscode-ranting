package com.github.ainul.twisdev.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(app: Application): AndroidViewModel(app) {

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