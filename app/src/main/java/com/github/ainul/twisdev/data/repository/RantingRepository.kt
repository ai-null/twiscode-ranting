package com.github.ainul.twisdev.data.repository

import com.github.ainul.twisdev.data.model.ItemModel
import com.github.ainul.twisdev.data.network.RantingNetwork
import com.github.ainul.twisdev.data.network.RantingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RantingRepository @Inject constructor(rantingNetwork: RantingNetwork) {
    private val service: RantingService = rantingNetwork.service

    suspend fun fetchDataFromNetwork(): List<ItemModel> {
        return withContext(Dispatchers.IO) { service.getListItem() }
    }
}