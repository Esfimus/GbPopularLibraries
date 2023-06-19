package com.esfimus.popularlibraries.mvp.presenter

import android.graphics.Bitmap
import android.util.Log
import com.esfimus.popularlibraries.mvp.model.GithubUser
import com.esfimus.popularlibraries.mvp.model.GithubUsersRepo
import com.esfimus.popularlibraries.mvp.view.UserItemView
import com.esfimus.popularlibraries.mvp.view.UsersView
import com.esfimus.popularlibraries.navigation.AndroidScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import moxy.MvpPresenter
import java.io.OutputStream

const val TAG = "@@@"

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : UserListPresenterInterface {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(AndroidScreens.Screens.selectedUser(usersListPresenter.users[itemView.pos]))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun reactiveConvertToPng(bitmap: Bitmap, outputStream: OutputStream) {
        Observable.fromSingle(Single.fromCallable { outputStream })
            .subscribe(
                { value ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, value)
                },
                { error -> Log.d(TAG, "Reactive $error") }
            )
            .dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}