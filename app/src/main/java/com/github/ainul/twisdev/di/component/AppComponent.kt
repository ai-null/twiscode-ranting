package com.github.ainul.twisdev.di.component

import com.github.ainul.twisdev.App
import com.github.ainul.twisdev.di.module.AppModule
import com.github.ainul.twisdev.data.network.RantingNetwork
import com.github.ainul.twisdev.ui.activity.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, RantingNetwork::class])
interface AppComponent {

    fun inject(app: App)
    fun inject(activity: MainActivity)
}