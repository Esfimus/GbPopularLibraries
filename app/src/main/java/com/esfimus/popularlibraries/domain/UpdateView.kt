package com.esfimus.popularlibraries.domain

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UpdateView: MvpView {
    fun setButtonText(count: String)
}