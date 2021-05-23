 package com.github.ainul.twisdev.network

import com.github.ainul.twisdev.network.interceptor.HttpInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://ranting.twisdev.com/index.php/rest/"
const val GET_DATA = "items/search/api_key/teampsisthebest/"

private val okHttpClient = OkHttpClient().newBuilder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(HttpInterceptor.httpLoggingInterceptor())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface RantingService {
    @POST(GET_DATA)
    suspend fun getListItem(): List<ItemModel>
}

object RantingNetwork {
    val service: RantingService by lazy {
        retrofit.create(RantingService::class.java)
    }
}

