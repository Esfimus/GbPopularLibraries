package com.esfimus.popularlibraries.di.repository

import com.esfimus.popularlibraries.mvp.presenter.RepositoryPresenter
import com.esfimus.popularlibraries.mvp.presenter.SelectedUserPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
    fun inject(selectedUserPresenter: SelectedUserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}