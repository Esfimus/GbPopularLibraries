package com.esfimus.popularlibraries.di

import com.esfimus.popularlibraries.di.module.ApiModule
import com.esfimus.popularlibraries.di.module.AppModule
import com.esfimus.popularlibraries.di.module.CacheModule
import com.esfimus.popularlibraries.di.module.CiceroneModule
import com.esfimus.popularlibraries.di.module.ImageModule
import com.esfimus.popularlibraries.di.module.RepoModule
import com.esfimus.popularlibraries.mvp.presenter.MainPresenter
import com.esfimus.popularlibraries.mvp.presenter.repositories.RepositoryPresenter
import com.esfimus.popularlibraries.mvp.presenter.user.UsersPresenter
import com.esfimus.popularlibraries.ui.activity.MainActivity
import com.esfimus.popularlibraries.ui.adapter.UserRecyclerAdapter
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
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
    fun inject(userRecyclerAdapter: UserRecyclerAdapter)
}