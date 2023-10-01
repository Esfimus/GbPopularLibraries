package com.esfimus.popularlibraries

import com.esfimus.popularlibraries.mvp.model.repo.user.GithubUsersRepoInterface
import com.esfimus.popularlibraries.mvp.presenter.user.UsersPresenter
import com.esfimus.popularlibraries.navigation.OpenUser
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UsersPresenterTest {
    private lateinit var presenter: UsersPresenter

    @Mock
    private lateinit var uiScheduler: Scheduler

    @Mock
    private lateinit var usersRepo: GithubUsersRepoInterface

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var openUser: OpenUser

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = UsersPresenter(uiScheduler, usersRepo, router, openUser)
    }

    @Test
    fun backPressed_Test() {
        presenter.backPressed()
        Mockito.verify(router, Mockito.times(1)).exit()
    }
}