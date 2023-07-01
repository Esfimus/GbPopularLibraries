package com.esfimus.popularlibraries.mvp.presenter

import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.view.RepositoryView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoryPresenter(private val repository: GithubRepository) : MvpPresenter<RepositoryView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setFullName("Full name: ${repository.fullName.toString()}")
        viewState.setDescription("Description: ${repository.description.toString()}")
        viewState.setCreatedAt("Created at: ${repository.createdAt.toString()}")
        viewState.setUpdatedAt("Updated at: ${repository.updatedAt.toString()}")
        viewState.setHomepage("Homepage: ${repository.homepage.toString()}")
        viewState.setLanguage("Language: ${repository.language.toString()}")
        viewState.setForksCount("Forks count: ${repository.forksCount.toString()}")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}