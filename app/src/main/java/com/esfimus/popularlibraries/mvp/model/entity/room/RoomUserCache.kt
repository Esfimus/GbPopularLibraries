package com.esfimus.popularlibraries.mvp.model.entity.room

import com.esfimus.popularlibraries.mvp.model.entity.room.database.Database
import com.esfimus.popularlibraries.mvp.model.entity.room.database.RoomGithubUser

class RoomUserCache : UserCacheInterface {

    private val db = Database.getInstance()

    override fun insertUsers(users: List<RoomGithubUser>) {
        db.userDao.insert(users)
    }

    override fun getUsers() = db.userDao.getAll()
}