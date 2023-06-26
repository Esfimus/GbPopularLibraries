package com.esfimus.popularlibraries.mvp.model.entity.room

import com.esfimus.popularlibraries.mvp.model.entity.room.database.RoomGithubRepository

interface RepositoriesCacheInterface {

    fun insertRepositories(repositories: List<RoomGithubRepository>)

    fun getUserId(login: String): String

    fun getRepositories(userId: String): List<RoomGithubRepository>
}