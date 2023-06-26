package com.esfimus.popularlibraries.mvp.model.entity.room

import com.esfimus.popularlibraries.mvp.model.entity.room.database.RoomGithubUser

interface UserCacheInterface {

    fun insertUsers(users: List<RoomGithubUser>)

    fun getUsers(): List<RoomGithubUser>
}