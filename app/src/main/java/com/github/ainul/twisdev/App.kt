package com.github.ainul.twisdev

import android.app.Application
import com.github.ainul.twisdev.di.component.AppComponent
import com.github.ainul.twisdev.di.module.AppModule
import com.github.ainul.twisdev.di.component.DaggerAppComponent

class App : Application() {

    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule()).build()
    }
}