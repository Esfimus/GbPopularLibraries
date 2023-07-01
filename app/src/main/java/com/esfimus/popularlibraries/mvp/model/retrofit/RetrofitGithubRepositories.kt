package com.esfimus.popularlibraries.mvp.model.retrofit

import com.esfimus.popularlibraries.mvp.model.api.DataSource
import com.esfimus.popularlibraries.mvp.model.cache.RepositoriesCache
import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

const val NO_REPOS_URL = "No repositories url"

class RetrofitGithubRepositories(
    private val api: DataSource,
    private val networkStatus: NetworkStatus,
    private val cache: RepositoriesCache
) : GithubRepositories {
    override fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let { url ->
                api.getRepositories(url).flatMap { repositories ->
                    cache.insertRepositories(user, repositories).toSingleDefault(repositories)
                }
            } ?: Single.error<List<GithubRepository>>(RuntimeException(NO_REPOS_URL)).subscribeOn(Schedulers.io())
        } else {
            cache.getRepositories(user)
        }
    }.subscribeOn(Schedulers.io())
}