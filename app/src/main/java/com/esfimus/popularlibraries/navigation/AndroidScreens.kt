package com.esfimus.popularlibraries.navigation

import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.ui.fragment.RepositoryFragment
import com.esfimus.popularlibraries.ui.fragment.SelectedUserFragment
import com.esfimus.popularlibraries.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : Screens {

    override fun users() =
        FragmentScreen { UsersFragment.newInstance() }

    override fun user(user: GithubUser) =
        FragmentScreen { SelectedUserFragment.newInstance(user) }

    override fun repository(repository: GithubRepository) =
        FragmentScreen { RepositoryFragment.newInstance(repository) }
}