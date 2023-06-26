package com.esfimus.popularlibraries.mvp.model.repo.repositories

import com.esfimus.popularlibraries.mvp.model.api.repositories.RepositorySourceInterface
import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.model.entity.room.Database
import com.esfimus.popularlibraries.mvp.model.entity.room.RoomGithubRepository
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatusInterface
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositories(
    private val api: RepositorySourceInterface,
    private val networkStatus: NetworkStatusInterface,
    private val db: Database
) : GithubUserRepositoryInterface {

    override fun getRepositories(login: String) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getRepositories(login).flatMap { repositories ->
                Single.fromCallable {
                    val roomRepositories = repositories.map { repository ->
                        RoomGithubRepository(
                            id = repository.id.toString(),
                            userId = db.userDao.findByLogin(login)?.id!!,
                            name = repository.name ?: "",
                            description = repository.description ?: "",
                            created = repository.createdAt ?: "",
                            updated = repository.updatedAt ?: "",
                            homepage = repository.homepage ?: "",
                            language = repository.language ?: "",
                            forks = repository.forksCount.toString()
                        )
                    }
                    db.repositoryDao.insert(roomRepositories)
                    repositories
                }
            }
        } else {
            Single.fromCallable {
                db.repositoryDao.findForUser(db.userDao.findByLogin(login)?.id!!).map {
                    GithubRepository(
                        id = it.id.toInt(),
                        name = it.name,
                        description = it.description,
                        createdAt = it.created,
                        updatedAt = it.updated,
                        homepage = it.homepage,
                        language = it.language
                    )
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}