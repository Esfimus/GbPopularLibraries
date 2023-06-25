package com.esfimus.popularlibraries.mvp.model.repo.repositories

import com.esfimus.popularlibraries.mvp.model.api.repositories.RepositorySourceInterface
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositories(private val api: RepositorySourceInterface) :
    GithubUserRepositoryInterface {
    override fun getRepositories(login: String) = api.getRepositories(login).subscribeOn(Schedulers.io())
}