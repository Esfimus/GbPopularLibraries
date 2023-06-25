package com.esfimus.popularlibraries.mvp.presenter

import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.model.repo.GithubUserRepositoryInterface
import com.esfimus.popularlibraries.mvp.view.RepositoryItemView
import com.esfimus.popularlibraries.mvp.view.RepositoryView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserRepositoryPresenter(
    private val uiScheduler: Scheduler,
    private val repository: GithubUserRepositoryInterface,
    private val router: Router,
    private val userLogin: String
) : MvpPresenter<RepositoryView>() {

    class RepositoryListPresenter : UserRepositoryListInterface {

        val repositories = mutableListOf<GithubRepository>()

        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setRepositoryName(it) }
        }

        override fun getCount() = repositories.size
    }

    val repositoryListPresenter = RepositoryListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData(userLogin)

        repositoryListPresenter.itemClickListener = {

        }
    }

    private fun loadData(login: String) {
        repository.getRepositories(login)
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                repositoryListPresenter.repositories.clear()
                repositoryListPresenter.repositories.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
            .isDisposed
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}