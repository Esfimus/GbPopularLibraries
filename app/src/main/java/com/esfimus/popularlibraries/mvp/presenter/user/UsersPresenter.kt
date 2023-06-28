package com.esfimus.popularlibraries.mvp.presenter.user

import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.mvp.model.repo.user.GithubUsersRepoInterface
import com.esfimus.popularlibraries.mvp.view.user.UserItemView
import com.esfimus.popularlibraries.mvp.view.user.UsersView
import com.esfimus.popularlibraries.navigation.OpenUser
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter(private val uiScheduler: Scheduler) : MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: GithubUsersRepoInterface
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var openUser: OpenUser

    class UsersListPresenter : UserListPresenterInterface {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(openUser.goToUser(usersListPresenter.users[itemView.pos]))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
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