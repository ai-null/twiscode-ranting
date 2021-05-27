package com.github.ainul.twisdev.di.component

import com.github.ainul.twisdev.App
import com.github.ainul.twisdev.di.module.AppModule
import com.github.ainul.twisdev.data.network.RetrofitBuilder
import com.github.ainul.twisdev.ui.main.view.activity.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, RetrofitBuilder::class])
interface AppComponent {

    fun inject(app: App)
    fun inject(activity: MainActivity)
}