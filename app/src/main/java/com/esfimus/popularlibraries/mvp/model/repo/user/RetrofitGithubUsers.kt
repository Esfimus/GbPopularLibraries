package com.esfimus.popularlibraries.mvp.model.repo.user

import com.esfimus.popularlibraries.mvp.model.api.user.DataSourceInterface
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.mvp.model.entity.room.database.RoomGithubUser
import com.esfimus.popularlibraries.mvp.model.entity.room.UserCacheInterface
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatusInterface
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsers(
    private val api: DataSourceInterface,
    private val networkStatus: NetworkStatusInterface,
    private val userCache: UserCacheInterface
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
                    userCache.insertUsers(roomUsers)
                    users
                }
            }
        } else {
            Single.fromCallable {
                userCache.getUsers()
                    .map { roomUser ->
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