package com.esfimus.popularlibraries.mvp.model.retrofit

import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubRepositories {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}