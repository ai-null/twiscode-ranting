package com.github.ainul.twisdev.data.network

import com.github.ainul.twisdev.data.network.interceptor.HttpInterceptor
import com.github.ainul.twisdev.util.Constants.BASE_URL
import dagger.Module
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
object RetrofitBuilder {
    // OkHttpClient setup
    private val okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpInterceptor.httpLoggingInterceptor())
        .build()

    // Retrofit setup
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: RantingService by lazy {
        retrofit.create(RantingService::class.java)
    }
}