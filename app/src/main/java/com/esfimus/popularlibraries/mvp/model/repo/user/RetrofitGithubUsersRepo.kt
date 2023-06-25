package com.esfimus.popularlibraries.mvp.model.repo.user

import com.esfimus.popularlibraries.mvp.model.api.user.DataSourceInterface
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val api: DataSourceInterface) : GithubUsersRepoInterface {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}