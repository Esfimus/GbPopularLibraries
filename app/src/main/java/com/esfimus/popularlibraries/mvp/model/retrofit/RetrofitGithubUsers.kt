package com.esfimus.popularlibraries.mvp.model.retrofit

import com.esfimus.popularlibraries.mvp.model.api.DataSource
import com.esfimus.popularlibraries.mvp.model.cache.UsersCache
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsers(
    private val api: DataSource,
    private val networkStatus: NetworkStatus,
    private val cache: UsersCache
) : GithubUsers {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                cache.insertUsers(users).toSingleDefault(users)
            }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}