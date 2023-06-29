package com.esfimus.popularlibraries

import android.app.Application
import com.esfimus.popularlibraries.di.AppComponent
import com.esfimus.popularlibraries.di.DaggerAppComponent
import com.esfimus.popularlibraries.di.module.AppModule
import com.esfimus.popularlibraries.mvp.model.entity.room.database.Database

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}