package com.esfimus.popularlibraries.di.user

import com.esfimus.popularlibraries.di.repository.RepositorySubcomponent
import com.esfimus.popularlibraries.mvp.presenter.UsersPresenter
import com.esfimus.popularlibraries.ui.adapter.UsersRecyclerAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRecyclerAdapter: UsersRecyclerAdapter)
}