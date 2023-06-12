package com.esfimus.popularlibraries.navigation

import com.esfimus.popularlibraries.ui.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : ScreensInterface {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}