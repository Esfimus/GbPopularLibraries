package com.esfimus.popularlibraries.mvp.presenter

import com.esfimus.popularlibraries.di.user.UserScopeContainer
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.mvp.model.retrofit.GithubUsers
import com.esfimus.popularlibraries.mvp.view.UserItemView
import com.esfimus.popularlibraries.mvp.view.UsersView
import com.esfimus.popularlibraries.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter : MvpPresenter<UsersView>() {

    @Inject
    lateinit var uiScheduler: Scheduler
    @Inject
    lateinit var githubUsers: GithubUsers
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: Screens
    @Inject
    lateinit var userScopeContainer: UserScopeContainer

    class UsersListPresenter : UserListPresenter {

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
            router.navigateTo(screens.user(usersListPresenter.users[itemView.pos]))
        }
    }

    private fun loadData() {
        githubUsers.getUsers()
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

    override fun onDestroy() {
        userScopeContainer.releaseUserScope()
        super.onDestroy()
    }
}