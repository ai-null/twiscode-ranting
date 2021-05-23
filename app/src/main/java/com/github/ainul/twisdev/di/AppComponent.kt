package com.github.ainul.twisdev.di

import com.github.ainul.twisdev.network.RantingNetwork
import com.github.ainul.twisdev.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RantingNetwork::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}