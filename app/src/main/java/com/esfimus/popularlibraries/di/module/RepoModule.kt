package com.esfimus.popularlibraries.di.module

import com.esfimus.popularlibraries.mvp.model.api.repositories.RepositorySourceInterface
import com.esfimus.popularlibraries.mvp.model.api.user.DataSourceInterface
import com.esfimus.popularlibraries.mvp.model.entity.room.RepositoriesCacheInterface
import com.esfimus.popularlibraries.mvp.model.entity.room.UserCacheInterface
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatusInterface
import com.esfimus.popularlibraries.mvp.model.repo.repositories.GithubUserRepositoryInterface
import com.esfimus.popularlibraries.mvp.model.repo.repositories.RetrofitGithubRepositories
import com.esfimus.popularlibraries.mvp.model.repo.user.GithubUsersRepoInterface
import com.esfimus.popularlibraries.mvp.model.repo.user.RetrofitGithubUsers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: DataSourceInterface,
        networkStatus: NetworkStatusInterface,
        cache: UserCacheInterface
    ): GithubUsersRepoInterface = RetrofitGithubUsers(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repository(
        apiRepositories: RepositorySourceInterface,
        networkStatus: NetworkStatusInterface,
        cache: RepositoriesCacheInterface
    ): GithubUserRepositoryInterface = RetrofitGithubRepositories(apiRepositories, networkStatus, cache)
}