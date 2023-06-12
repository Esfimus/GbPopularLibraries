package com.esfimus.popularlibraries.mvp.model

class GithubUsersRepo {
    private val users = listOf(
        GithubUser("user1"),
        GithubUser("user2"),
        GithubUser("user3"),
        GithubUser("user4"),
        GithubUser("user5"),
        GithubUser("user6"),
        GithubUser("user7"),
        GithubUser("user8"),
    )

    fun getUsers() = users
}