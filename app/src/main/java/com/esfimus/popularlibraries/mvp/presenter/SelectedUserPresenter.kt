package com.esfimus.popularlibraries.mvp.presenter

import com.esfimus.popularlibraries.di.repository.RepositoryScopeContainer
import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.mvp.model.retrofit.GithubRepositories
import com.esfimus.popularlibraries.mvp.view.RepositoryItemView
import com.esfimus.popularlibraries.mvp.view.UserView
import com.esfimus.popularlibraries.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class SelectedUserPresenter(val user: GithubUser) : MvpPresenter<UserView>() {

    @Inject
    lateinit var uiScheduler: Scheduler
    @Inject
    lateinit var githubRepositories: GithubRepositories
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: Screens
    @Inject
    lateinit var repositoryScopeContainer: RepositoryScopeContainer

    class RepositoriesListPresenter : RepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null
        override fun getCount() = repositories.size
        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            repository.fullName?.let { view.setRepositoryName(it) }
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        user.login?.let { viewState.setLogin(it) }
        repositoriesListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.repository(repositoriesListPresenter.repositories[itemView.pos]))
        }
    }

    private fun loadData() {
        githubRepositories.getRepositories(user)
            .observeOn(uiScheduler)
            .subscribe({ repositories ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repositories)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            }).isDisposed
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        repositoryScopeContainer.releaseRepositoryScope()
        super.onDestroy()
    }
}