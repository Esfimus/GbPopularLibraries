package com.esfimus.popularlibraries.di.repository

import com.esfimus.popularlibraries.App
import com.esfimus.popularlibraries.mvp.model.api.DataSource
import com.esfimus.popularlibraries.mvp.model.cache.RepositoriesCache
import com.esfimus.popularlibraries.mvp.model.cache.RoomRepositoriesCache
import com.esfimus.popularlibraries.mvp.model.entity.room.Database
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatus
import com.esfimus.popularlibraries.mvp.model.retrofit.GithubRepositories
import com.esfimus.popularlibraries.mvp.model.retrofit.RetrofitGithubRepositories
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {

    @Provides
    fun repositoriesCache(database: Database): RepositoriesCache = RoomRepositoriesCache(database)

    @RepositoryScope
    @Provides
    fun githubRepositories(
        api: DataSource,
        networkStatus: NetworkStatus,
        cache: RepositoriesCache
    ): GithubRepositories = RetrofitGithubRepositories(api, networkStatus, cache)

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): RepositoryScopeContainer = app
}