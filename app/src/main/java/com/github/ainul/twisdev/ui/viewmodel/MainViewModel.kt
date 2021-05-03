package com.github.ainul.twisdev.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val _actionBarHidden = MutableLiveData<Boolean>()
    val actionBarHidden: LiveData<Boolean> get() = _actionBarHidden

    fun hideActionBar() {
        _actionBarHidden.value = _actionBarHidden.value != true
    }
}