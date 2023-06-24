package com.esfimus.popularlibraries

import android.app.Application
import com.esfimus.popularlibraries.navigation.AndroidScreens
import com.esfimus.popularlibraries.navigation.OpenUser
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    val openUser: OpenUser = AndroidScreens()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}