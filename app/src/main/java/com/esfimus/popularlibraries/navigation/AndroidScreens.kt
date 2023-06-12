package com.esfimus.popularlibraries.navigation

import com.esfimus.popularlibraries.mvp.model.GithubUser
import com.esfimus.popularlibraries.ui.SelectedUserFragment
import com.esfimus.popularlibraries.ui.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : ScreensInterface {

    object Screens {
        fun selectedUser(user: GithubUser) = FragmentScreen { SelectedUserFragment.newInstance(user) }
    }

    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}