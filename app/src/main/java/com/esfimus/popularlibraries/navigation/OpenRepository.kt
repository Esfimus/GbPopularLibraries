package com.esfimus.popularlibraries.navigation

import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.github.terrakok.cicerone.Screen

interface OpenRepository {
    fun goToRepository(repository: GithubRepository) : Screen
}