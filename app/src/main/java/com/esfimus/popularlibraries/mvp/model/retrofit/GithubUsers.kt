package com.esfimus.popularlibraries.mvp.model.retrofit

import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubUsers {
    fun getUsers(): Single<List<GithubUser>>
}