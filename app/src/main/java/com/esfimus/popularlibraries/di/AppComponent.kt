package com.esfimus.popularlibraries.di

import com.esfimus.popularlibraries.di.module.ApiModule
import com.esfimus.popularlibraries.di.module.AppModule
import com.esfimus.popularlibraries.di.module.CacheModule
import com.esfimus.popularlibraries.di.module.CiceroneModule
import com.esfimus.popularlibraries.di.module.ImageModule
import com.esfimus.popularlibraries.di.user.UserSubcomponent
import com.esfimus.popularlibraries.mvp.presenter.MainPresenter
import com.esfimus.popularlibraries.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        ImageModule::class,
    ]
)
interface AppComponent {
    fun userSubcomponent(): UserSubcomponent

    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
}