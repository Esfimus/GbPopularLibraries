package com.esfimus.popularlibraries.mvp.presenter

import com.esfimus.popularlibraries.mvp.view.MainView
import com.esfimus.popularlibraries.navigation.ScreensInterface
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: ScreensInterface) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}