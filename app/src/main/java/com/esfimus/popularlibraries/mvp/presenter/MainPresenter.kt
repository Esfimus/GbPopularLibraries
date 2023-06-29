package com.esfimus.popularlibraries.mvp.presenter

import com.esfimus.popularlibraries.mvp.view.MainView
import com.esfimus.popularlibraries.navigation.ScreensInterface
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: ScreensInterface

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}