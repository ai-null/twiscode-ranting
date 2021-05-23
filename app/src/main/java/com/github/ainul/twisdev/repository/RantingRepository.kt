package com.github.ainul.twisdev.repository

import com.github.ainul.twisdev.network.ItemModel
import com.github.ainul.twisdev.network.RantingNetwork
import com.github.ainul.twisdev.network.RantingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RantingRepository @Inject constructor(rantingNetwork: RantingNetwork) {
    private val service: RantingService = rantingNetwork.service

    suspend fun fetchDataFromNetwork(): List<ItemModel> {
        return withContext(Dispatchers.IO) { service.getListItem() }
    }
}