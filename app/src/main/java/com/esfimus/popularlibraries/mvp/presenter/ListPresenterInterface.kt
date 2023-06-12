package com.esfimus.popularlibraries.mvp.presenter

import com.esfimus.popularlibraries.mvp.view.ItemView

interface ListPresenterInterface<V : ItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}