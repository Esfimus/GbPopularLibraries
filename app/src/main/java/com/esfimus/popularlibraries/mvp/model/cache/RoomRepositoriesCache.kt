package com.esfimus.popularlibraries.mvp.model.cache

import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.mvp.model.entity.room.Database
import com.esfimus.popularlibraries.mvp.model.entity.room.RoomGithubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

const val NO_USER = "No user in cache"

class RoomRepositoriesCache(private val db: Database) : RepositoriesCache {
    override fun insertRepositories(
        user: GithubUser,
        repositories: List<GithubRepository>
    ) = Completable.fromAction {
        val roomUser = db.userDao.findByLogin(user.login!!) ?: throw RuntimeException(NO_USER)
        val roomRepositories = repositories.map {
            RoomGithubRepository(
                id = it.id.toString(),
                userId = roomUser.id,
                name = it.fullName ?: "",
                description = it.description ?: "",
                created = it.createdAt ?: "",
                updated = it.updatedAt ?: "",
                homepage = it.homepage ?: "",
                language = it.language ?: "",
                forks = it.forksCount.toString()
            )
        }
        db.repositoryDao.insert(roomRepositories)
    }.subscribeOn(Schedulers.io())

    override fun getRepositories(user: GithubUser) = Single.fromCallable {
        val roomUser = db.userDao.findByLogin(user.login!!) ?: throw RuntimeException(NO_USER)
        return@fromCallable db.repositoryDao.findForUser(roomUser.id)
            .map { GithubRepository(
                id = it.id.toIntOrNull(),
                fullName = it.name,
                description = it.description,
                createdAt = it.created,
                updatedAt = it.updated,
                homepage = it.homepage,
                language = it.language,
                forksCount = it.forks.toInt()
            ) }
    }.subscribeOn(Schedulers.io())
}