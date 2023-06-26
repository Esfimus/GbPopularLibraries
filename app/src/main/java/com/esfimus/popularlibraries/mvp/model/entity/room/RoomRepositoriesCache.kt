package com.esfimus.popularlibraries.mvp.model.entity.room

import com.esfimus.popularlibraries.mvp.model.entity.room.database.Database
import com.esfimus.popularlibraries.mvp.model.entity.room.database.RoomGithubRepository

class RoomRepositoriesCache : RepositoriesCacheInterface {

    private val db = Database.getInstance()

    override fun insertRepositories(repositories: List<RoomGithubRepository>) {
        db.repositoryDao.insert(repositories)
    }

    override fun getUserId(login: String) = db.userDao.findByLogin(login)?.id!!

    override fun getRepositories(userId: String) = db.repositoryDao.findForUser(userId)

}