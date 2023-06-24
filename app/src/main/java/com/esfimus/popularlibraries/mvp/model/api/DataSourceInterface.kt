package com.esfimus.popularlibraries.mvp.model.api

import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DataSourceInterface {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
}