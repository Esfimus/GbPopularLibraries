package com.esfimus.popularlibraries.mvp.model.repo.user

import com.esfimus.popularlibraries.mvp.model.api.user.DataSourceInterface
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.mvp.model.entity.room.Database
import com.esfimus.popularlibraries.mvp.model.entity.room.RoomGithubUser
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatusInterface
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val api: DataSourceInterface,
    private val networkStatus: NetworkStatusInterface,
    private val db: Database
) : GithubUsersRepoInterface {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                Single.fromCallable {
                    val roomUsers = users.map { user ->
                        RoomGithubUser(
                            id = user.id ?: "",
                            login = user.login ?: "",
                            avatarUrl = user.avatarUrl ?: "",
                        )
                    }
                    db.userDao.insert(roomUsers)
                    users
                }
            }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(
                        id = roomUser.id,
                        login = roomUser.login,
                        avatarUrl = roomUser.avatarUrl,
                    )
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}