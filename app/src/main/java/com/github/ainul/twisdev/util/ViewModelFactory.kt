package com.github.ainul.twisdev.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.ainul.twisdev.data.repository.RantingRepository
import com.github.ainul.twisdev.ui.main.viewmodel.MainViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: RantingRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Unable to construct ${modelClass.name}")
    }
}