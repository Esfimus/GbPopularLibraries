package com.esfimus.popularlibraries.mvp.model.api

import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositorySourceInterface {
    @GET("{user}/repos")
    fun getRepositories(@Path("user") userLogin: String): Single<List<GithubRepository>>
}