package com.esfimus.popularlibraries

import android.app.Application
import com.esfimus.popularlibraries.di.AppComponent
import com.esfimus.popularlibraries.di.DaggerAppComponent
import com.esfimus.popularlibraries.di.module.AppModule
import com.esfimus.popularlibraries.di.repository.RepositoryScopeContainer
import com.esfimus.popularlibraries.di.repository.RepositorySubcomponent
import com.esfimus.popularlibraries.di.user.UserScopeContainer
import com.esfimus.popularlibraries.di.user.UserSubcomponent

class App : Application(), UserScopeContainer, RepositoryScopeContainer {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    private var userSubcomponent: UserSubcomponent? = null

    var repositorySubcomponent: RepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun initRepositorySubcomponent() = userSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }

    override fun releaseUserScope() {
        userSubcomponent = null
    }
}