package com.esfimus.popularlibraries.di.module

import com.esfimus.popularlibraries.navigation.AndroidScreens
import com.esfimus.popularlibraries.navigation.OpenRepository
import com.esfimus.popularlibraries.navigation.OpenUser
import com.esfimus.popularlibraries.navigation.ScreensInterface
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun cicerone(): Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router(): Router = cicerone.router

    @Singleton
    @Provides
    fun screens(): ScreensInterface = AndroidScreens()

    @Singleton
    @Provides
    fun openUser(): OpenUser = AndroidScreens()

    @Singleton
    @Provides
    fun openRepository(): OpenRepository = AndroidScreens()
}