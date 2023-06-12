package com.esfimus.popularlibraries.mvp.presenter

import com.esfimus.popularlibraries.mvp.model.GithubUser
import com.esfimus.popularlibraries.mvp.model.GithubUsersRepo
import com.esfimus.popularlibraries.mvp.view.MainView
import com.esfimus.popularlibraries.mvp.view.UserItemView
import moxy.MvpPresenter

class MainPresenter(private val usersRepo: GithubUsersRepo) : MvpPresenter<MainView>() {

    class UsersListPresenter : UserListPresenterInterface {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            itemView.setLogin("CLICKED")
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
}