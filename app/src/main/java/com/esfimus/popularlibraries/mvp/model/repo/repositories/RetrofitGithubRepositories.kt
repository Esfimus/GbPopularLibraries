package com.esfimus.popularlibraries.mvp.model.repo.repositories

import com.esfimus.popularlibraries.mvp.model.api.repositories.RepositorySourceInterface
import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.model.entity.room.RepositoriesCacheInterface
import com.esfimus.popularlibraries.mvp.model.entity.room.database.RoomGithubRepository
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatusInterface
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositories(
    private val api: RepositorySourceInterface,
    private val networkStatus: NetworkStatusInterface,
    private val repositoryCache: RepositoriesCacheInterface
) : GithubUserRepositoryInterface {

    override fun getRepositories(login: String) = networkStatus.isOnlineSingle().flatMap { isOnline ->

        val userId = repositoryCache.getUserId(login)

        if (isOnline) {
            api.getRepositories(login).flatMap { repositories ->
                Single.fromCallable {
                    val roomRepositories = repositories.map { repository ->
                        RoomGithubRepository(
                            id = repository.id.toString(),
                            userId = userId,
                            name = repository.fullName ?: "",
                            description = repository.description ?: "",
                            created = repository.createdAt ?: "",
                            updated = repository.updatedAt ?: "",
                            homepage = repository.homepage ?: "",
                            language = repository.language ?: "",
                            forks = repository.forksCount.toString()
                        )
                    }
                    repositoryCache.insertRepositories(roomRepositories)
                    repositories
                }
            }
        } else {
            Single.fromCallable {
                repositoryCache.getRepositories(userId)
                    .map {
                    GithubRepository(
                        id = it.id.toIntOrNull(),
                        fullName = it.name,
                        description = it.description,
                        createdAt = it.created,
                        updatedAt = it.updated,
                        homepage = it.homepage,
                        language = it.language,
                        forksCount = it.forks.toInt()
                    )
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}