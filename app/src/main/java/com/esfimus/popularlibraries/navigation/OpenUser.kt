package com.esfimus.popularlibraries.navigation

import com.esfimus.popularlibraries.mvp.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface OpenUser {
    fun goToUser(user: GithubUser) : Screen
}