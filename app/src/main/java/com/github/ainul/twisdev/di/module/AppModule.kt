package com.github.ainul.twisdev.di.module

import com.github.ainul.twisdev.data.network.RetrofitBuilder
import com.github.ainul.twisdev.data.network.RantingService
import dagger.Module
import dagger.Provides

@Module
class AppModule() {

    @Provides
    fun provideService(): RantingService {
        return RetrofitBuilder.service
    }
}