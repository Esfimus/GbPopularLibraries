package com.esfimus.popularlibraries.mvp.model.cache

import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UsersCache {
    fun insertUsers(users: List<GithubUser>): Completable
    fun getUsers(): Single<List<GithubUser>>
}