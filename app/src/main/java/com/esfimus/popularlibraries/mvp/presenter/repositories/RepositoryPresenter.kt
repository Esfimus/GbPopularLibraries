package com.esfimus.popularlibraries.mvp.presenter.repositories

import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.model.repo.repositories.GithubUserRepositoryInterface
import com.esfimus.popularlibraries.mvp.view.repositories.RepositoryItemView
import com.esfimus.popularlibraries.mvp.view.repositories.RepositoryView
import com.esfimus.popularlibraries.navigation.OpenRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoryPresenter(private val userLogin: String) : MvpPresenter<RepositoryView>() {

    @Inject
    lateinit var uiScheduler: Scheduler
    @Inject
    lateinit var repository: GithubUserRepositoryInterface
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var openRepository: OpenRepository

    class RepositoryListPresenterPresenter : RepositoryListPresenterInterface {

        val repositories = mutableListOf<GithubRepository>()

        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            repository.fullName?.let { view.setRepositoryName(it) }
        }

        override fun getCount() = repositories.size
    }

    val repositoryListPresenter = RepositoryListPresenterPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData(userLogin)

        repositoryListPresenter.itemClickListener = { itemView ->
            router.navigateTo(openRepository.goToRepository(repositoryListPresenter.repositories[itemView.pos]))
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