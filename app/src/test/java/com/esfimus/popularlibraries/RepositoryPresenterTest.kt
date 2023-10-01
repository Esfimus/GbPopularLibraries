package com.esfimus.popularlibraries

import com.esfimus.popularlibraries.mvp.model.repo.repositories.GithubUserRepositoryInterface
import com.esfimus.popularlibraries.mvp.presenter.repositories.RepositoryPresenter
import com.esfimus.popularlibraries.navigation.OpenRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class RepositoryPresenterTest {
    private lateinit var presenter: RepositoryPresenter

    @Mock
    private lateinit var uiScheduler: Scheduler

    @Mock
    private lateinit var repository: GithubUserRepositoryInterface

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var openRepository: OpenRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = RepositoryPresenter(uiScheduler, repository, router, openRepository, "login")
    }

    @Test
    fun backPressed_Test() {
        presenter.backPressed()
        verify(router, times(1)).exit()
    }
}