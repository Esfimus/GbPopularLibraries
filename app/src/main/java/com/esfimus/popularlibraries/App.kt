package com.esfimus.popularlibraries

import android.app.Application
import com.esfimus.popularlibraries.mvp.model.entity.room.Database
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatusInterface
import com.esfimus.popularlibraries.navigation.AndroidScreens
import com.esfimus.popularlibraries.navigation.OpenRepository
import com.esfimus.popularlibraries.navigation.OpenUser
import com.esfimus.popularlibraries.ui.network.AndroidNetworkStatus
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var networkStatus: NetworkStatusInterface
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    val openUser: OpenUser = AndroidScreens()
    val openRepository: OpenRepository = AndroidScreens()

    override fun onCreate() {
        super.onCreate()
        instance = this
        networkStatus = AndroidNetworkStatus(instance)
        Database.create(this)
    }
}