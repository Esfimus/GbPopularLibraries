package com.esfimus.popularlibraries.mvp.presenter

import android.annotation.SuppressLint
import com.esfimus.popularlibraries.mvp.model.GithubUser
import com.esfimus.popularlibraries.mvp.model.GithubUsersRepo
import com.esfimus.popularlibraries.mvp.view.UserItemView
import com.esfimus.popularlibraries.mvp.view.UsersView
import com.esfimus.popularlibraries.navigation.AndroidScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : UserListPresenterInterface {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        @SuppressLint("CheckResult")
        override fun bindView(view: UserItemView) {
            Observable.just(users[view.pos])
                .map { it.login.uppercase() }
                .flatMap {
                    val delay = Random.nextInt(2000).toLong()
                    return@flatMap Observable.just("delayed $it").delay(delay, TimeUnit.MILLISECONDS)
                }
                .subscribe(
                    { value ->
                        view.setLogin("Received: $value")
                    },
                    { error -> println("Error: $error") }
                )
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(AndroidScreens.Screens.selectedUser(
                getReactiveUser(usersListPresenter.users[itemView.pos])
            ))
        }
    }

    @SuppressLint("CheckResult")
    private fun getReactiveUser(user: GithubUser): GithubUser {
        var reactiveUser = GithubUser("")
        Observable.just(user)
            .map { it.login.uppercase() }
            .subscribe(
                { value ->
                    val reactiveLogin = "REACTIVE LOGIN $value"
                    reactiveUser = GithubUser(reactiveLogin)
                },
                { error -> println("Error: $error") }
            )
        return reactiveUser
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()

        reactiveSwitchMap()
        reactiveFlatMap()
    }

    @SuppressLint("CheckResult")
    private fun reactiveSwitchMap() {
        val reactiveList = mutableListOf<String>()
        Observable.fromIterable(listOf("A", "B", "C", "D", "E", "F", "G", "H"))
            .switchMap {
                val delay = Random.nextInt(2000).toLong()
                return@switchMap Observable.just("$it$it$it").delay(delay, TimeUnit.MILLISECONDS)
            }
            .subscribe(
                { value ->
                    reactiveList.add(value)
                    println("switchMap: $reactiveList")
                },
                { error -> println("Error: $error") }
            )
    }

    @SuppressLint("CheckResult")
    private fun reactiveFlatMap() {
        val reactiveList = mutableListOf<String>()
        Observable.fromIterable(listOf("A", "B", "C", "D", "E", "F", "G", "H"))
            .flatMap {
                val delay = Random.nextInt(2000).toLong()
                return@flatMap Observable.just("$it$it$it").delay(delay, TimeUnit.MILLISECONDS)
            }
            .subscribe(
                { value ->
                    reactiveList.add(value)
                    println("flatMap $reactiveList")
                },
                { error -> println("Error: $error") }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}