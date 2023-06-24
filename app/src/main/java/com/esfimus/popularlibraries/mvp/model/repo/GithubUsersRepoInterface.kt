package com.esfimus.popularlibraries.mvp.model.repo

import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepoInterface {
    fun getUsers(): Single<List<GithubUser>>
}