package com.esfimus.popularlibraries.di.user

import com.esfimus.popularlibraries.App
import com.esfimus.popularlibraries.mvp.model.api.DataSource
import com.esfimus.popularlibraries.mvp.model.cache.RoomUsersCache
import com.esfimus.popularlibraries.mvp.model.cache.UsersCache
import com.esfimus.popularlibraries.mvp.model.entity.room.Database
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatus
import com.esfimus.popularlibraries.mvp.model.retrofit.GithubUsers
import com.esfimus.popularlibraries.mvp.model.retrofit.RetrofitGithubUsers
import dagger.Module
import dagger.Provides

@Module
open class UserModule {

    @Provides
    fun usersCache(database: Database): UsersCache = RoomUsersCache(database)

    @UserScope
    @Provides
    open fun githubUsers(
        api: DataSource,
        networkStatus: NetworkStatus,
        cache: UsersCache
    ): GithubUsers = RetrofitGithubUsers(api, networkStatus, cache)

    @UserScope
    @Provides
    open fun scopeContainer(app: App): UserScopeContainer = app
}