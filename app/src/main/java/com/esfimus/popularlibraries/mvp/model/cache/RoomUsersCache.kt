package com.esfimus.popularlibraries.mvp.model.cache

import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.mvp.model.entity.room.Database
import com.esfimus.popularlibraries.mvp.model.entity.room.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomUsersCache(private val db: Database) : UsersCache {
    override fun insertUsers(users: List<GithubUser>) = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                id = user.id ?: "",
                login = user.login ?: "",
                avatarUrl = user.avatarUrl ?: "",
                reposUrl = user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers .io())

    override fun getUsers() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(
                id = roomUser.id,
                login = roomUser.login,
                avatarUrl = roomUser.avatarUrl,
                reposUrl = roomUser.reposUrl
            )
        }
    }.subscribeOn(Schedulers.io())
}