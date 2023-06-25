package com.esfimus.popularlibraries.mvp.model.repo.repositories

import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import io.reactivex.rxjava3.core.Single

interface GithubUserRepositoryInterface {
    fun getRepositories(login: String): Single<List<GithubRepository>>
}