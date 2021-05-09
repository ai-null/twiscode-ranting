package com.github.ainul.twisdev.repository

import com.github.ainul.twisdev.network.RantingNetwork
import com.github.ainul.twisdev.network.RantingService

class RantingRepository {
    private val service: RantingService = RantingNetwork.service

    suspend fun fetchDataFromNetwork() = service.getListItem()
}