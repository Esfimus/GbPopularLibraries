package com.esfimus.popularlibraries.mvp.model.cache

import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface RepositoriesCache {
    fun insertRepositories(user: GithubUser, repositories: List<GithubRepository>): Completable
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}