package com.github.ainul.twisdev.di.module

import com.github.ainul.twisdev.network.RantingNetwork
import dagger.Module
import dagger.Provides

@Module
class AppModule() {

    @Provides
    fun provideNetwork() = RantingNetwork
}