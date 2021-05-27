 package com.github.ainul.twisdev.data.network

import com.github.ainul.twisdev.data.model.ItemModel
import com.github.ainul.twisdev.util.Constants.GET_DATA
import retrofit2.http.POST

interface RantingService {
    @POST(GET_DATA)
    suspend fun getListItem(): List<ItemModel>
}

