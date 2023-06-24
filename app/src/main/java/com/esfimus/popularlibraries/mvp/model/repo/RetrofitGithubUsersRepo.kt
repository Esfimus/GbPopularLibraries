package com.esfimus.popularlibraries.mvp.model.repo

import com.esfimus.popularlibraries.mvp.model.api.DataSourceInterface
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val api: DataSourceInterface) : GithubUsersRepoInterface {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}