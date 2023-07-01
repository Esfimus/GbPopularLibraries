package com.esfimus.popularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView : MvpView {
    fun init()
    fun setFullName(name: String)
    fun setDescription(description: String)
    fun setCreatedAt(createdAt: String)
    fun setUpdatedAt(updatedAt: String)
    fun setHomepage(homepage: String)
    fun setLanguage(language: String)
    fun setForksCount(forks: String)
}