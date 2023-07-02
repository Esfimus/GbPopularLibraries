package com.esfimus.popularlibraries.navigation

import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.github.terrakok.cicerone.Screen

interface Screens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
    fun repository(repository: GithubRepository): Screen
}