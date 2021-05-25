package com.github.ainul.twisdev.data.network.interceptor

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

object HttpInterceptor {
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("INTERCEPTOR", "okhttp $message")
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}