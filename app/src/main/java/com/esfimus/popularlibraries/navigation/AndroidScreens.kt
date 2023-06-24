package com.esfimus.popularlibraries.navigation

import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.ui.SelectedUserFragment
import com.esfimus.popularlibraries.ui.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : ScreensInterface, OpenUser {

    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun goToUser(user: GithubUser) = FragmentScreen { SelectedUserFragment.newInstance(user) }

}