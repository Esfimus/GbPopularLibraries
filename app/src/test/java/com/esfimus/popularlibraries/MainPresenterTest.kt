package com.esfimus.popularlibraries

import com.esfimus.popularlibraries.mvp.presenter.MainPresenter
import com.esfimus.popularlibraries.mvp.view.MainView
import com.esfimus.popularlibraries.navigation.ScreensInterface
import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

class MainPresenterTest {
    private lateinit var presenter: MainPresenter

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var screens: ScreensInterface

    @Mock
    private lateinit var view: MainView

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = MainPresenter(router, screens)
    }

    @Test
    fun backClicked_Test() {
        presenter.backClicked()
        verify(router, times(1)).exit()
    }

    @Test
    fun onFirstViewAttach_Test() {
        presenter.attachView(view)
        verify(router, times(1)).replaceScreen(screens.users())
    }
}